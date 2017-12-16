package fr.isepconseil.dao;

import fr.isepconseil.vo.User;

public interface IUserDAO {
	public boolean findLogin(User user) throws Exception; 
}
