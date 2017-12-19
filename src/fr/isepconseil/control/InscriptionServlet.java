package fr.isepconseil.control;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		Connection connexion = null;
		Statement statement = null;
		ResultSet ajout = null;

		response.setContentType("text/html");
		try {
			dbc = new DatabaseConnection();
			connexion = dbc.getConnection();
			statement = connexion.createStatement();

			PrintWriter out = response.getWriter();
			String iPrenom = request.getParameter("prenom");
			String iNom = request.getParameter("nom");
			String iAnnee = request.getParameter("annee");
			String iAlternance = request.getParameter("alternance");
			String iPromotion = request.getParameter("promotion");
			String iParcours = request.getParameter("parcours");
			String iEmail = request.getParameter("email");
			String iPassword = request.getParameter("password");
			String iConPassword = request.getParameter("ConfirmationPassword");
			String info = null;
			if ((iPrenom == null || "".equals(iPrenom))||(iNom == null || "".equals(iNom))||(iEmail == null
					|| "".equals(iEmail))||(iPassword == null || "".equals(iPassword))) {
				info = "La connexion a echoue, merci d'essayer de nouveau";
			}
			ajout = statement.executeQuery( "Insert into Users(firstName, lastName, email, password) values ('"+iPrenom+"','"+ iNom+"','" +iEmail+"','" +iPassword+"') " );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {

			if ( ajout != null ) {
				try {

					ajout.close();

				} catch ( SQLException ignore ) {
				}
			}
			System.out.println("Fermeture du resulset");
			if ( statement != null ) {
				try {

					statement.close();
				} catch ( SQLException ignore ) {
				}
			}
			System.out.println("Fermeture du statement");

			if ( connexion != null ) {
				try {

					connexion.close();
				} catch ( SQLException ignore ) {
				}
			}
			System.out.println("Fermeture du connection");



		}

	}}
