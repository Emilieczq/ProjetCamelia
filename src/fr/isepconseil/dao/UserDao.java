package fr.isepconseil.dao;

import fr.isepconseil.vo.User;

public interface UserDao {
	public boolean findLogin(User user) throws Exception; 
}
