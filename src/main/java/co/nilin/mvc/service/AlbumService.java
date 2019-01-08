package co.nilin.mvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.nilin.mvc.data.entity.Album;
import co.nilin.mvc.data.entity.User;
import co.nilin.mvc.repository.AlbumRepository;
import co.nilin.mvc.repository.UserRepository;

@Component
public class AlbumService implements IAlbumService {

	@Autowired
	private AlbumRepository albumRepo;
	
	@Autowired
	private UserRepository usrrepo;
	
	@Override
	public List<Album> findAlbumsByUserName(String userName) {
		List<Album> albums=new ArrayList<Album>();
		for(Album a:albumRepo.findAll()) {
			if(a.getCreator().getUserName().equals(userName))albums.add(a);
		}
		return albums;
	}

	@Override
	public void addAlbum(Long userId, String name) {
		Album al =new Album();
		Optional<User> op=usrrepo.findById(userId);
		al.setCreator(op.get());
		al.setName(name);
		albumRepo.save(al);

	}

	@Override
	public Album findAlbumById(Long albumId) {
		Album album=albumRepo.findById(albumId).get();
		return album;
	}

	@Override
	public List<Album> findAll() {
		List<Album> albums=new ArrayList<Album>();
		for(Album a:albumRepo.findAll())albums.add(a);
		
		return albums;
	}

	@Override
	public void deleteAlbum(Long albumId) {
		albumRepo.deleteById(albumId);
		
	}
	@Override
	public void updateAlbum(Album album){
		albumRepo.save(album);
	}

}
