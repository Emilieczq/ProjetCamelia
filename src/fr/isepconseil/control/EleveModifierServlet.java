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
import javax.servlet.http.HttpSession;

import fr.isepconseil.dbc.DatabaseConnection;
import fr.isepconseil.vo.Etudiant;
import fr.isepconseil.vo.Professeur;
import fr.isepconseil.vo.User;
import fr.isepconseil.dao.UserDAOI;

/**
 * Servlet implementation class EleveModifierServlet
 */
@WebServlet("/EleveModifierServlet")
public class EleveModifierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseConnection dbc = null;
	private Connection connexion = null;
	private Statement statement = null;
	private Statement statement1 = null;
	private Statement statement2 = null;
	private Statement statement12 = null;
	private Statement statement3 = null;
	private Statement statement13 = null;
	private Statement statement4 = null;
	private Statement statement14 = null;
	private ResultSet resultat = null;
	private int modif;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EleveModifierServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			dbc = new DatabaseConnection();
			connexion = dbc.getConnection();
			statement = connexion.createStatement();
			statement1 = connexion.createStatement();
			statement2 = connexion.createStatement();
			statement12 = connexion.createStatement();
			statement3 = connexion.createStatement();
			statement13 = connexion.createStatement();
			statement4 = connexion.createStatement();
			statement14 = connexion.createStatement();
			HttpSession session = request.getSession(true);

			PrintWriter out = response.getWriter();
			String mPromo = request.getParameter("promotion");
			String mParcours = request.getParameter("parcours");
			String mToeic = request.getParameter("toeic");
			String sAlternance = request.getParameter("alternance");
			System.out.println("Alternance " + sAlternance);
			int iAlternance = 1;
			if (sAlternance != null && sAlternance.equals("alternance")) {
				iAlternance = 0;
			}
			User user = new User();
			Etudiant etudiant = (Etudiant) request.getSession().getAttribute(
					"etudiant");
			String iemail = etudiant.getEmail();
			String ipassword = user.getPassword();

			if (mPromo != null) {
				resultat = statement1.executeQuery("select id_User from Users where email = '"
						+ iemail + "';");
				System.out.println("Marche1"); // test a delete
				while (resultat.next()) {
					int idUser = resultat.getInt("id_User");
					modif = statement
							.executeUpdate("Update Students set studyyear = '" + mPromo
									+ "' where id_User = '" + idUser + "';");

				}
			}
			if(mParcours !=null){
				resultat = statement12
						.executeQuery("select id_User from Users where email = '"
								+ iemail + "';");
				System.out.println("Marche2"); // test a delete
				while (resultat.next()) {
					int idUser = resultat.getInt("id_User");
					modif = statement2
							.executeUpdate("Update Students set parcours = '"
									+ mParcours + "' where id_User = '" + idUser + "';");			
				}
			}

			if(mPromo != null){
				resultat = statement13
						.executeQuery("select id_User from Users where email = '"
								+ iemail + "';");
				System.out.println("Marche3"); // test a delete
				while (resultat.next()) {
					int idUser = resultat.getInt("id_User");
					modif = statement3
							.executeUpdate("Update Students set studyyear = '" + mPromo
									+ "' where id_User = '" + idUser + "';");
				}
			}

			if(mToeic != null && !mToeic.equals("")){
				System.out.println(mToeic);
				resultat = statement13
						.executeQuery("select id_User from Users where email = '"
								+ iemail + "';");
				System.out.println("Marche4"); // test a delete
				while (resultat.next()) {
					int idUser = resultat.getInt("id_User");
					modif = statement3
							.executeUpdate("Update Students set toeic = '" + mToeic
									+ "' where id_User = '" + idUser + "';");
				}
			}
			resultat = statement14
					.executeQuery("select id_User from Users where email = '"
							+ iemail + "';");
			System.out.println("Marche1"); // test a delete
			while (resultat.next()) {
				int idUser = resultat.getInt("id_User");
				modif = statement4
						.executeUpdate("Update Students set alternance = '" + iAlternance
								+ "' where id_User = '" + idUser + "';");
			}
			

				user.setEmail(iemail);
				user.setPassword(ipassword);
				request.setAttribute("user", user);
				UserDAOI userDAO = new UserDAOI();
				Etudiant etudiant2 = new Etudiant();
				userDAO.setEtudiant(user, etudiant2);
				etudiant = userDAO.getEtudiant();
				session.setAttribute("etudiant", etudiant);

				
				
				//Gestion des stages
				
/*				String s1annee = request.getParameter("annee2");
				String s1Firm = request.getParameter("HEntreprises");
				String s1Job = request.getParameter("HPoste");
*/
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/ProfilPourEleve.jsp");
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
				if ( statement4 != null ) {
					try {

						statement4.close();
						System.out.println("Fermeture du statement");

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
				if ( statement12 != null ) {
					try {

						statement12.close();
						System.out.println("Fermeture du statement");

					} catch ( SQLException ignore ) {
					}
				}
				if ( statement13 != null ) {
					try {

						statement13.close();
						System.out.println("Fermeture du statement");

					} catch ( SQLException ignore ) {
					}
				}
				if ( statement14!= null ) {
					try {

						statement14.close();
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
		}
	}



