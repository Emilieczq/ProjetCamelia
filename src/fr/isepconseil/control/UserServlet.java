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

		String info = null;
		if ((login == null || "".equals(login))||(password == null || "".equals(password))) {
			info = "La connexion a échoué, merci d'essayer de nouveau";
			System.out.println("login ou passworld vide"); // to be deleted
		}
		if (info == null) {
			User user = new User();
			user.setEmail(login);
			user.setPassword(password);
			request.setAttribute("user", user);
			UserDAOProxy userDAOProxy = new UserDAOProxy();

			try {

				if (userDAOProxy.findLogin(user)) {
					System.out.println("right login and password"); // to be deleted
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/recherchePourEleve.jsp");
					dispatcher.forward(request, response);

				} else {
					user = new User(); // clear all the information in JavaBean => not sure
					System.out.println("wrong login or wrong password"); // to be deleted
					info="La connexion a échoué, merci d'essayer de nouveau";
					request.setAttribute("info", info);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("info", info);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}
}
