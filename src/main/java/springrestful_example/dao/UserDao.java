package springrestful_example.dao;

import java.util.List;

import springrestful_example.model.User;

public interface UserDao {
	
	public List<User> listAllUser();
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void delete(User user);
	
	public User findById(User user);

}
