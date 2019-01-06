package co.nilin.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.nilin.mvc.data.entity.Album;
import co.nilin.mvc.data.entity.Picture;
import co.nilin.mvc.repository.AlbumRepository;
import co.nilin.mvc.repository.PictureRepository;

@Component
public class PictureService implements IPictureService {

    @Autowired
    private PictureRepository picrepo;
    @Autowired
    private AlbumRepository albumrepo;


    @Override
    public Picture findById(Long picId) {
        Picture pic = picrepo.findById(picId).get();
        return pic;
    }

    @Override
    public List<Picture> findPicByAlbumId(Long albumId) {
        List<Picture> pics = new ArrayList<Picture>();
        for (Picture p : picrepo.findAll()) if (p.getPicAlbum().equals(albumrepo.findById(albumId).get())) pics.add(p);
        return pics;
    }

    @Override
    public List<Picture> findPicByUserId(Long id) {
        List<Album> albums = new ArrayList<Album>();
        List<Picture> pics = new ArrayList<Picture>();
        for (Album a : albumrepo.findAll()) if (a.getCreator().getId().equals(id)) albums.add(a);
        for (Album a : albums) {

            for (Picture p : picrepo.findAll()) {
                if (p.getPicAlbum().equals(a)) pics.add(p);
            }
        }
        return pics;
    }

    @Override
    public List<Picture> findPicByUserName(String userName) {
        List<Album> albums = new ArrayList<Album>();
        List<Picture> pics = new ArrayList<Picture>();
        for (Album a : albumrepo.findAll()) if (a.getCreator().getUserName().equals(userName)) albums.add(a);
        for (Album a : albums) {

            for (Picture p : picrepo.findAll()) {
                if (p.getPicAlbum().equals(a)) pics.add(p);
            }
        }
        return pics;
    }


    @Override
    public void addPicture(byte[] file, String comment, Album picAlbum) {
        Picture pic = new Picture();
        pic.setComment(comment);
        pic.setImage(file);
        pic.setPicAlbum(picAlbum);
        picrepo.save(pic);


    }

    @Override
    public void deletePicture(Long id) {
        picrepo.deleteById(id);

    }

    @Override
    public void addPicture(byte[] file, String comment, Long picAlbumId) {
        Picture pic = new Picture();
        pic.setImage(file);
        pic.setPicAlbum(albumrepo.findById(picAlbumId).get());
        pic.setComment(comment);
        picrepo.save(pic);

    }

    @Override
    public void updatePic(Picture pic) {
        picrepo.save(pic);
    }

}
