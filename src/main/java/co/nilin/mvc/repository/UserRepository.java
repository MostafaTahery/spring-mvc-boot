package co.nilin.mvc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.nilin.mvc.data.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
