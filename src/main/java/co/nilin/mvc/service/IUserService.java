package co.nilin.mvc.service;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import co.nilin.mvc.data.entity.User;

@Component
public interface IUserService {

	public List<User> findAll();
	public User addUser(String fullName, String userName, String passWord, String email, Date birthDate);
	public Boolean isValid(String username,String password);
	public Boolean isAvailable(String userName);
	public Long findIdByUserName(String userName);
	User findById(Long id);
	void updateUser(User user);
}
