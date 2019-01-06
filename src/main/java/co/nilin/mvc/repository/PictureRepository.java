package co.nilin.mvc.repository;

import org.springframework.data.repository.CrudRepository;

import co.nilin.mvc.data.entity.Picture;

public interface PictureRepository extends CrudRepository<Picture, Long> {

}
