package fr.isepconseil.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.isepconseil.dbc.DatabaseConnection;

@WebServlet("/RechercheServlet")
public class RechercheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseConnection dbc = null;
	private Connection connexion = null;
	private Statement statement = null, statement2 = null;
	
	private ResultSet rset, rset2;

	public RechercheServlet() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			dbc = new DatabaseConnection();
			connexion = dbc.getConnection();
			statement = connexion.createStatement();
			statement2 = connexion.createStatement();
			
			String recherche = request.getParameter("search");
			
			response.setContentType("text/html");
			List<String> results = new ArrayList<String>();
			List<Integer> ids = new ArrayList<Integer>();
			List<String> types = new ArrayList<String>();
			
			if (recherche != null ) {
				/*
				 * we can search any students by email or firstName+" "+lastName or lastName+" "+firstName
				 * ignore we write in upper case or in lower case
				 * no need for only firstName or only lastName, because they are included in this case and the following case
				 */
				rset = statement.executeQuery("select distinct * from Users where"
						+ "(email like '%"+recherche+"%' "
						+ "or lower(concat(firstName,'' '',lastName)) like '%"+recherche+"%'" // no need for only firstName or only lastName, because they are included in this case and the following case
						+ "or lower(concat(lastName,'' '',firstName)) like '%"+recherche+"%'"
						+ ");");
						
				while (rset.next()){
					results.add(rset.getString( "firstName" ) + " " + rset.getString( "lastName" ));
					ids.add(rset.getInt( "id_User" ));
					
					rset2 = statement2.executeQuery("select * from Students where id_User=" + rset.getInt( "id_User" ) +";");
					if(rset2.next()) {
						types.add("student");
					}else {
						types.add("teacher");
					}
				}
			}
			
			request.setAttribute("results", results); 
			request.setAttribute("ids", ids);
			request.setAttribute("types", types);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/resarchResult.jsp"); 
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("Exception declenchee");
			e.printStackTrace();
		}
		finally {
			if ( rset != null ) {
				try {
					rset.close();
					System.out.println("Fermeture du resultat");
				} catch ( SQLException ignore ) {
				}
			}
			if ( rset2 != null ) {
				try {
					rset2.close();
					System.out.println("Fermeture du resultat");
				} catch ( SQLException ignore ) {
				}
			}
			if ( statement != null ) {
				try {
					statement.close();
					System.out.println("Fermeture du statement");
				} catch ( SQLException ignore ) {
				}
			}
			if ( statement2 != null ) {
				try {
					statement2.close();
					System.out.println("Fermeture du statement");
				} catch ( SQLException ignore ) {
				}
			}
		}
	}
}