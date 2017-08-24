package springrestful_example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springrestful_example.dao.UserDao;
import springrestful_example.model.User;

@Service
public class UserServiceImpl implements UserService {

	UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> listAllUser() {
		return userDao.listAllUser();
	}

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void delete(User user) {
		userDao.delete(user);
	}

	public User findById(User user) {
		return userDao.findById(user);
	}

}
