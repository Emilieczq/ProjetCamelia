package fr.isepconseil.control;

import java.io.IOException;
import java.io.PrintWriter;
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
	private Statement statement = null;
	
	private ResultSet rset;

	public RechercheServlet() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			dbc = new DatabaseConnection();
			connexion = dbc.getConnection();
			statement = connexion.createStatement();
			
			String recherche = request.getParameter("search");
//			recherche.toLowerCase(null);
			System.out.println(recherche);
			
			response.setContentType("text/html");
			List<String> results = new ArrayList<String>();
			List<String> ids = new ArrayList<String>();
			if (recherche != null ) {
				/*
				 * we can search any students by email or firstName+" "+lastName or lastName+" "+firstName
				 * ignore we write in upper case or in lower case
				 */
				rset = statement.executeQuery("select distinct * from Users where"
						+ "(email like '%"+recherche+"%' "
						+ "or lower(concat(firstName,'' '',lastName)) like '%"+recherche+"%'" // no need for only firstName or only lastName, because they are included in this case and the following case
						+ "or lower(concat(lastName,'' '',firstName)) like '%"+recherche+"%'"
						+ ");");
						
				while (rset.next()){
					System.out.println("result" + rset.getString( "firstName" )); // à supprimer
					results.add(rset.getString( "firstName" ) + " " + rset.getString( "lastName" ));
					ids.add(rset.getInt( "id_User" )+"");
				}
			}
			
			request.setAttribute("results", results); 
			request.setAttribute("ids", ids);
			
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
			if ( statement != null ) {
				try {
					statement.close();
					System.out.println("Fermeture du statement");
				} catch ( SQLException ignore ) {
				}
			}
		}
	}
}
