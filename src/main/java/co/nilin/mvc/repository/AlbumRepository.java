package co.nilin.mvc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.nilin.mvc.data.entity.Album;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

}
