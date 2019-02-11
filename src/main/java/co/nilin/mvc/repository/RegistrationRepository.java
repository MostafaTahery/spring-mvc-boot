package co.nilin.mvc.repository;

import co.nilin.mvc.data.entity.Registration;
import org.springframework.data.repository.CrudRepository;

public interface RegistrationRepository extends CrudRepository<Registration,Long> {
}
