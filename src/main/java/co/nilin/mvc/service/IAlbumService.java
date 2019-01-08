package co.nilin.mvc.service;

import java.util.List;

import org.springframework.stereotype.Component;

import co.nilin.mvc.data.entity.Album;

@Component
public interface IAlbumService {

	List<Album> findAll();
	Album findAlbumById(Long albumId);
	List<Album> findAlbumsByUserName(String userName);
	void addAlbum(Long userId,String name);
	void deleteAlbum(Long id);
	void updateAlbum(Album album);
}
