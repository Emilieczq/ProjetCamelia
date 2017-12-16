package fr.isepconseil.control;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.isepconseil.dao.UserDAOProxy;
import fr.isepconseil.vo.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // need to download MySQL JDBC jar
    static final String DB_URL = "jdbc:mysql://localhost:3306/Camelia"; // correct the project name

    static final String USER = "root"; // your server user name
    static final String PASSWORD = "123456"; // your password
    
    public UserServlet() {
        super();
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login"); // login => email
		String password = request.getParameter("password");
		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
		
	    List<String> info=new ArrayList<String>();  
	    if(login==null||"".equals(login)){ //用户名输入格式问题  
	        info.add("Login ne peut pas être vide !");  
	        System.out.println("Login ne peut pas être vide !");  
	    }  
	  
	    if(password==null||"".equals(password)){//密码输入格式问题  
	        info.add("Mot de passe ne peut pas être vide !");  
	        System.out.println("Mot de passe ne peut pas être vide !");  
	    }  
	    if(info.size()==0){  
	        User user=new User();  
	        user.setEmail(login);  
	        user.setPassword(password);  
	        UserDAOProxy userDAOProxy=new UserDAOProxy();  
	        try {  
	              
	            if(userDAOProxy.findLogin(user)){  
//	                info.add("用户登录成功，欢迎"+user.getEmail()+"光临！"); 
	                System.out.println("right");
	                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProfilPourEleve.jsp");
	                dispatcher.forward(request,response);//跳转
	                
	            }else {  
//	                info.add("用户登录失败，错误的用户名和密码"); 
	            		System.out.println("wrong");
	            }                         
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
//	    request.setAttribute("info", info);//保存错误信息  
	    
	    
		
		
		
		
	}

}
