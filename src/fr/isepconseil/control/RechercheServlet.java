package fr.isepconseil.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.isepconseil.dbc.DatabaseConnection;

/**
 * Servlet implementation class RechercheServlet
 */
@WebServlet("/RechercheServlet")
public class RechercheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseConnection dbc = null;
	private Connection connexion = null;
	private Statement statement = null;
	private ResultSet resultat;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RechercheServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			dbc = new DatabaseConnection();
			connexion = dbc.getConnection();
			statement = connexion.createStatement();

			PrintWriter out = response.getWriter();
			String recherche = request.getParameter("search");
			System.out.println(recherche);


			if (recherche != null ) {
				resultat = statement.executeQuery("select firstName from Users where email = '"+recherche+"';");
				while (resultat.next()){
					String nameRech = resultat.getString( "firstName" );

				}

			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp"); //a modifier, page de resultat
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception declenchee");
			e.printStackTrace();
		}
		finally {

			if ( resultat != null ) {
				try {

					resultat.close();
					System.out.println("Fermeture du resulset");

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
