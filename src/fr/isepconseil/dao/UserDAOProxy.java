package fr.isepconseil.dao;

import fr.isepconseil.dbc.DatabaseConnection;
import fr.isepconseil.vo.User;

public class UserDAOProxy implements IUserDAO {
	private DatabaseConnection dbc=null;  
    private IUserDAO dao=null; 
	
    public UserDAOProxy(){  
        try {  
            dbc=new DatabaseConnection(); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        dao=new UserDAOImp(dbc.getConnection());  
          
    }  
	@Override
	public boolean findLogin(User user) throws Exception {
		boolean flag=false;  
        try {  
            flag=dao.findLogin(user);
        } catch (Exception e) {  
            throw e;  
        }finally{  
            dbc.close();  
        }  
        return flag;  
	}
}
