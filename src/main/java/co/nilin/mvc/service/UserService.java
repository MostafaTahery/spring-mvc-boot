package co.nilin.mvc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.nilin.mvc.data.entity.User;
import co.nilin.mvc.repository.UserRepository;

@Component
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public List<User> findAll() {
		List<User> users=(List<User>)userRepository.findAll();
		return users;
	}
	@Override
	public User addUser(String fullName, String userName, String passWord, String email, Date birthDate) {
		User user=new User();
		user.setBirthDate(birthDate);
		user.setEmail(email);
		user.setFullName(fullName);
		user.setPassword(passWord);
		user.setUserName(userName);
		userRepository.save(user);
		System.out.println("user added");

		return user;
	}
	@Override
	public Boolean isValid(String userName, String passWord) {
		
		for(User e:userRepository.findAll()) {
			if(e.getUserName().equals(userName)&&(e.getPassword().equals(passWord)))return true;
		}
		return false;
	}
	@Override
	public Boolean isAvailable(String userName) {
		
		for(User e:userRepository.findAll()) {
			if(e.getUserName().equals(userName))return false;
		}
		return true;
	}
	@Override
	public Long findIdByUserName(String userName) {
		for(User u:userRepository.findAll())if(u.getUserName().equals(userName))return u.getId();
		return null;
	}
	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
		
	}
	@Override
	public void updateUser(User user) {
		userRepository.save(user);
		
	}

}
