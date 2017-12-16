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
	    if(login==null||"".equals(login)){ 
	        info.add("Login ne peut pas être vide !");  
	        System.out.println("Login ne peut pas être vide !");  
	    }  
	  
	    if(password==null||"".equals(password)){ 
	        info.add("Mot de passe ne peut pas être vide !");  
	        System.out.println("Mot de passe ne peut pas être vide !");  
	    }  
	    if(info.size()==0){  
	        User user=new User();  
	        user.setEmail(login);  
	        user.setPassword(password);  
	        request.setAttribute("user", user);
	        UserDAOProxy userDAOProxy=new UserDAOProxy(); 
	        
	        try {  
	              
	            if(userDAOProxy.findLogin(user)){  
	                System.out.println("right");
	                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Welcome.jsp");
	                dispatcher.forward(request,response);
	                
	            }else {  
	            		System.out.println("wrong");
	            }                         
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	    
       

		
		
		
		
	}

}
