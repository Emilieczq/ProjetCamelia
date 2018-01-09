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
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseConnection dbc = null;
	private Connection connexion = null;
	private Statement statement1 = null;
	private Statement statement2 = null;
	private Statement statement3 = null;
	private ResultSet resultat;
	private int ajout;
	private int ajout2;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InscriptionServlet() {
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


		response.setContentType("text/html");
		try {
			dbc = new DatabaseConnection();
			connexion = dbc.getConnection();
			statement1 = connexion.createStatement();
			statement2 = connexion.createStatement();
			statement3 = connexion.createStatement();

			PrintWriter out = response.getWriter();
			String iPrenom = request.getParameter("prenom");
			String iNom = request.getParameter("nom");
			String iAnnee = request.getParameter("annee");
			String sAlternance = request.getParameter("alternance");
			System.out.println(sAlternance);
			int iAlternance = 1;
			if (sAlternance == "alternance"){
				iAlternance = 0;
			}

			String iParcours = request.getParameter("parcours");
			String iEmail = request.getParameter("email");
			String iPassword = request.getParameter("password");

				

			if (iPrenom != null || iNom != null ||iEmail != null
				||iPassword != null ||sAlternance != null ||iParcours != null||iAnnee != null  ) {
				ajout = statement1.executeUpdate( "Insert into Users(email, password,firstName, lastName) values ('" +iEmail+"','" +iPassword+"', '"+iPrenom+"','"+ iNom+"');");
				resultat = statement2.executeQuery("select id_User from Users where email = '"+iEmail+"';");
				System.out.println("Marche1");
				while (resultat.next()){
					int idUtilisateur = resultat.getInt( "id_User" );
					
				    ajout2 = statement3.executeUpdate( "Insert into Students(id_User, parcours, studyyear, alternance) values ('" +idUtilisateur+"','" +iParcours+"','" +iAnnee+"', '"+iAlternance+"');");
				}
		        			
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Vue/JSP/index.jsp");
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
			if ( statement1 != null ) {
				try {

					statement1.close();
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
			if ( statement3 != null ) {
				try {

					statement3.close();
					System.out.println("Fermeture du statement");

				} catch ( SQLException ignore ) {
				}
			}

			if ( connexion != null ) {
				try {

					connexion.close();
					System.out.println("Fermeture du connection");

				} catch ( SQLException ignore ) {
				}
			}



		}

	}}
