package co.nilin.mvc.service;

import java.util.List;

import org.springframework.stereotype.Component;

import co.nilin.mvc.data.entity.Album;
import co.nilin.mvc.data.entity.Picture;

@Component
public interface IPictureService {
Picture findById(Long picId);
List<Picture> findPicByAlbumId(Long albumId);
List<Picture> findPicByUserId(Long id);
List<Picture> findPicByUserName(String userName);
void addPicture(byte[] file,String comment,Long picAlbumId); 
void deletePicture(Long Id);
void addPicture(byte[] file, String comment, Album picAlbum);
void updatePic(Picture pic);
}
