package co.nilin.mvc;

import co.nilin.mvc.data.dto.RegisterDto;
import co.nilin.mvc.data.entity.Album;
import co.nilin.mvc.data.entity.Picture;
import co.nilin.mvc.data.dto.SimpleDto;
import co.nilin.mvc.data.entity.User;
import co.nilin.mvc.service.AlbumService;
import co.nilin.mvc.service.PictureService;
import co.nilin.mvc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.net.URI;
import java.sql.Date;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/rest")
@EnableCaching
public class RestController {

    @Autowired
    public UserService usrservice;
    @Autowired
    public PictureService picsrvc;
    @Autowired
    public AlbumService albmsrvc;

    public String verificationCode;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/signup", method = RequestMethod.POST, params = {"fullName", "userName", "passWord", "pass2", "email", "birthDate"})
    @ResponseBody
    public User addUser(@RequestParam("fullName") String fullName,
                        @RequestParam("userName") String userName, @RequestParam("passWord") String passWord, @RequestParam("pass2") String pass2,
                        @RequestParam("email") String email, @RequestParam("birthDate") String birthDate) {

        if (usrservice.isAvailable(userName) && (pass2.equals(passWord))) {
            User usr = usrservice.addUser(fullName, userName, passWord, email, Date.valueOf(birthDate));
            return usr;
        } else {
            return null;
        }
    }


    @RequestMapping(value = "/user/full_name", method = RequestMethod.PATCH, params = {"userId", "fullName"})
    @ResponseBody
    public User editFullName(@RequestParam("userId") String userId, @RequestParam("fullName") String fullName) {
        User usr = usrservice.findById(Long.valueOf(userId));
        usr.setFullName(fullName);
        usrservice.updateUser(usr);
        return usr;
    }

    @RequestMapping(value = "/user/user_name", method = RequestMethod.PATCH, params = {"userId", "userName"})
    @ResponseBody
    public User editUserName(@RequestParam("userId") String userId, @RequestParam("userName") String userName) {
        User usr = usrservice.findById(Long.valueOf(userId));
        usr.setUserName(userName);
        usrservice.updateUser(usr);
        return usr;
    }

    @RequestMapping(value = "/user/pass_word", method = RequestMethod.PATCH, params = {"userId", "passWord"})
    @ResponseBody
    public User editPassWord(@RequestParam("userId") String userId, @RequestParam("passWord") String passWord) {
        User usr = usrservice.findById(Long.valueOf(userId));
        usr.setPassword(passWord);
        usrservice.updateUser(usr);
        return usr;
    }

    @RequestMapping(value = "/user/email", method = RequestMethod.PATCH, params = {"userId", "email"})
    @ResponseBody
    public User editEmail(@RequestParam("userId") String userId, @RequestParam("Email") String email) {
        User usr = usrservice.findById(Long.valueOf(userId));
        usr.setEmail(email);
        usrservice.updateUser(usr);
        return usr;
    }

    @RequestMapping(value = "/user/birth_date", method = RequestMethod.PATCH, params = {"userId", "birthDate"})
    @ResponseBody
    public User editBirthDate(@RequestParam("userId") String userId, @RequestParam("birthDate") String birthDate) {
        User usr = usrservice.findById(Long.valueOf(userId));
        usr.setBirthDate(Date.valueOf(birthDate));
        usrservice.updateUser(usr);
        return usr;
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, params = {"fullName", "userName", "passWord", "pass2", "email", "birthDate"})
    public User editprofile(@RequestParam("fullName") String fullName,
                            @RequestParam("userName") String userName, @RequestParam("passWord") String passWord, @RequestParam("pass2") String pass2,
                            @RequestParam("email") String email, @RequestParam("birthDate") String birthDate) {
        User usr = usrservice.findById(usrservice.findIdByUserName(userName));
        if (pass2.equals(passWord)) {
            usr.setUserName(userName);
            usr.setFullName(fullName);
            usr.setBirthDate(Date.valueOf(birthDate));
            usr.setEmail(email);
            usr.setPassword(passWord);
            usrservice.updateUser(usr);
            return usr;
        } else return usr;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST, params = {"name_in", "pass_in"}, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    public User signinUser(@RequestParam("name_in") String name_in, @RequestParam("pass_in") String pass_in) {
        User usr = usrservice.findById(usrservice.findIdByUserName(name_in));

        if (usrservice.isValid(name_in, pass_in)) {
            return usr;
        } else {
            return null;
        }
    }

    @GetMapping(value = "/album/{albumId}", produces = {"application/json"})
    @ResponseBody
    public Album getAlbumInfo(@PathVariable("albumId") String albumId) {
        Album album = albmsrvc.findAlbumById(Long.parseLong(albumId));
        System.out.println("hooooy");
        logger.debug("Hoo Hoo Hoo! ! !");
        return album;
    }

    @RequestMapping(value = "/album", method = RequestMethod.POST, params = {"albumName", "creatorId"}, produces = {"application/json"})
    @ResponseBody
    public Album makeAlbum(@RequestParam("albumName") String albumName, @RequestParam("creatorId") String creatorId) {
        Album album = new Album();
        album.setCreator(usrservice.findById(Long.parseLong(creatorId)));
        album.setName(albumName);
        albmsrvc.updateAlbum(album);
        return album;
    }

    @RequestMapping(value = "/album/album_name", method = RequestMethod.PATCH, params = {"albumId", "albumName"}, produces = {"application/json"})
    @ResponseBody
    public Album editAlbumName(@RequestParam("albumId") String albumId, @RequestParam("albumName") String albumName) {
        Album album = albmsrvc.findAlbumById(Long.valueOf(albumId));
        album.setName(albumName);
        albmsrvc.updateAlbum(album);
        return album;
    }

    @RequestMapping(value = "/Album/creator", method = RequestMethod.PATCH, params = {"albumId", "creator"}, produces = {"application/json"})
    @ResponseBody
    public Album editAlbumCreator(@RequestParam("albumId") String albumId, @RequestParam("creator") String creator) {
        Album album = albmsrvc.findAlbumById(Long.valueOf(albumId));
        album.setCreator(usrservice.findById(Long.valueOf(creator)));
        albmsrvc.updateAlbum(album);
        return album;
    }

    @Cacheable(value = "post-single", key = "#creator")
    @GetMapping(value = "/albums/{creator}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Album> getUserAlbums(@PathVariable("creator") String creator) {
        List<Album> albums = albmsrvc.findAlbumsByUserName(creator);
        System.out.println("aaaaaaaaaaaaaaahhhhhhhhhhhhhhh");
        return albums;
    }

    @CachePut(value = "albumId", key = "#albumId")
    @GetMapping(value = "/Pics/{albumId}", produces = {"application/json"})
    @ResponseBody
    public List<Picture> getAlbumPics(@PathVariable("albumId") String albumId) {
        List<Picture> pics = picsrvc.findPicByAlbumId(Long.valueOf(albumId));
        return pics;
    }

    @RequestMapping(value = "/pic", method = RequestMethod.POST, params = {"comment", "albumId", "file"}, consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    public Picture addPic(@RequestParam("comment") String comment, @RequestParam("albumId") String albumId, @RequestParam("file") MultipartFile file) {
        Picture pic = new Picture();
        pic.setComment(comment);
        pic.setPicAlbum(albmsrvc.findAlbumById(Long.valueOf(albumId)));
        if (!file.isEmpty()) {
            try {
                byte[] bb = file.getBytes();
                pic.setImage(bb);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("A File Error has happened");
            }
        }
        return pic;
    }

    @RequestMapping(value = "/pic/comment", method = RequestMethod.PATCH, params = {"picId", "comment"}, produces = {"application/json"})
    @ResponseBody
    public Picture editComment(@RequestParam("picId") String picId, @RequestParam("comment") String comment) {
        Picture pic = picsrvc.findById(Long.valueOf(picId));
        pic.setComment(comment);
        picsrvc.updatePic(pic);
        return pic;
    }


    @RequestMapping(value = "/pic/image", method = RequestMethod.PATCH, params = {"picId", "picImage"}, produces = {"application/json"})
    @ResponseBody
    public Picture editPicImage(@RequestParam("picId") String picId, @RequestParam("picImage") MultipartFile picImage) {
        Picture pic = picsrvc.findById(Long.valueOf(picId));
        try {
            pic.setImage(picImage.getBytes());
            picsrvc.updatePic(pic);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pic;
    }

    @RequestMapping(value = "/pic/album", method = RequestMethod.PATCH, params = {"picId", "picAlbum"}, produces = {"application/json"})
    @ResponseBody
    public Picture editPicAlbum(@RequestParam("picId") String picId, @RequestParam("picAlbumId") String picAlbumId) {
        Picture pic = picsrvc.findById(Long.valueOf(picId));
        pic.setPicAlbum(albmsrvc.findAlbumById(Long.valueOf(picAlbumId)));
        picsrvc.updatePic(pic);
        return pic;
    }

    @GetMapping("/test")
    @ResponseBody()
    public SimpleDto test() {
        logger.debug("Logging Test");
        return new SimpleDto();
    }

    @RequestMapping(value = "/register", params = "receptor", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam("receptor") String receptor) {
        verificationCode = String.valueOf((Math.round(Math.random() * 10000)));
        RegisterDto registerDto = new RegisterDto();
        registerDto.setMessage(verificationCode);
        registerDto.setReceptor(receptor);
        System.out.println(registerDto.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("receptor", registerDto.getReceptor());
        multiValueMap.add("message", registerDto.getMessage());
        HttpEntity<MultiValueMap<String, Object>> registerDtoHttpEntity = new HttpEntity<MultiValueMap<String, Object>>(multiValueMap, headers);
        String url = "https://api.kavenegar.com/v1/49634F384F6752574B654F3261756E744B5A4A6C57513D3D/sms/send";
        //+registerDto.toString();
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(uri, HttpMethod.POST, registerDtoHttpEntity, String.class);
        return verificationCode;
    }

    @RequestMapping(value = "/verify",method = RequestMethod.POST,params = "vcode")
    public String verify(@RequestParam("vcode") String verificationCode ){
        String status;
        if(this.verificationCode.equals(verificationCode))status="Verified";
        else status="Not Verified";
        return status;
    }


}
