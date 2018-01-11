package fr.isepconseil.control;

import java.io.IOException;
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

	public EleveModifierServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

			String mPromo = request.getParameter("promotion");
			String mParcours = request.getParameter("parcours");
			String mToeic = request.getParameter("toeic");
			String sAlternance = request.getParameter("alternance");
			System.out.println("Alternance " + sAlternance);
			int iAlternance = 0;		// J'ai remplacé 1 par 0. Je pense que c'est plus logique: non-alternance = 0; alternance = 1;
			if (sAlternance != null && sAlternance.equals("alternance")) {
				iAlternance = 1;
			}
			User user = new User();
			Etudiant etudiant = (Etudiant) request.getSession().getAttribute("etudiant");
			String iemail = etudiant.getEmail();

			/*
			 * too many statements, 
			 * Peut-être c'est mieux de update tous les information à la fois directement 
			 * sans comparer les données précédents, du coup, un statement est suffisant
			 */
			
			if (mPromo != null) {
				resultat = statement1.executeQuery("select id_User from Users where email = '" + iemail + "';");
				System.out.println("Marche1"); // test a delete
				while (resultat.next()) {
					int idUser = resultat.getInt("id_User");
					modif = statement.executeUpdate(
							"Update Students set studyyear = '" + mPromo + "' where id_User = '" + idUser + "';"); 
					// => studyyear et promotion sont différents

				}
			}
			if (mParcours != null) {
				resultat = statement12.executeQuery("select id_User from Users where email = '" + iemail + "';");
				System.out.println("Marche2"); // test a delete
				while (resultat.next()) {
					int idUser = resultat.getInt("id_User");
					modif = statement2.executeUpdate(
							"Update Students set parcours = '" + mParcours + "' where id_User = '" + idUser + "';");
					etudiant.setParcours(mParcours); // chaque fois, quand tu changes BBD, tu changes session aussi.
				}
			}

			if (mPromo != null) { // egale à statement 12 ??
				resultat = statement13.executeQuery("select id_User from Users where email = '" + iemail + "';");
				System.out.println("Marche3"); // test a delete
				while (resultat.next()) {
					int idUser = resultat.getInt("id_User");
					modif = statement3.executeUpdate(
							"Update Students set studyyear = '" + mPromo + "' where id_User = '" + idUser + "';");
				}
			}

			if (mToeic != null && !mToeic.equals("")) {
				System.out.println(mToeic);
				resultat = statement13.executeQuery("select id_User from Users where email = '" + iemail + "';");
				System.out.println("Marche4"); // test a delete
				while (resultat.next()) {
					int idUser = resultat.getInt("id_User");
					modif = statement3.executeUpdate(
							"Update Students set toeic = '" + mToeic + "' where id_User = '" + idUser + "';");
					etudiant.setToeic(Integer.parseInt(mToeic)); // comme parcours
				}
			}else {
				// => update toeic to 0
				// => etudiant.setToeic(0);
			}
			resultat = statement14.executeQuery("select id_User from Users where email = '" + iemail + "';");
			System.out.println("Marche1"); // test a delete
			while (resultat.next()) {
				int idUser = resultat.getInt("id_User");
				modif = statement4.executeUpdate(
						"Update Students set alternance = '" + iAlternance + "' where id_User = '" + idUser + "';");
				// il manque setAlternance(int), parametre est 1 ou 0
			}

			user.setEmail(iemail);
			session.setAttribute("user", user); // => user in session, not request (état faux dans userDao, mais corrigé à mon coté
			/*
			 * inutile à utiliser userDao. userDao est seulement utilisé dans login
			 * une fois user et etudiant ou professeur sont créé, si on change des données, on changes sur l'objet directement, pas créer un nouveau
			 */
			// UserDAOI userDAO = new UserDAOI();
			// Etudiant etudiant2 = new Etudiant();
			// userDAO.setEtudiant(user, etudiant2);
			// etudiant = userDAO.getEtudiant();
			
			session.setAttribute("etudiant", etudiant); // => comme tu as déjà modifié etudiant, quand tu utilises setAttribute, il va remplacé ce "nouveau" etudiant

			
			
			// Gestion des stages

			/*
			 * String s1annee = request.getParameter("annee2"); String s1Firm =
			 * request.getParameter("HEntreprises"); String s1Job =
			 * request.getParameter("HPoste");
			 */
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProfilPourEleve.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Exception declenchee");
			e.printStackTrace();
		} finally {
			if (resultat != null) {
				try {
					resultat.close();
					System.out.println("Fermeture du resulset");
				} catch (SQLException ignore) {
				}

			}
			if (statement1 != null) {
				try {
					statement1.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}
			if (statement2 != null) {
				try {
					statement2.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}
			if (statement3 != null) {
				try {
					statement3.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}
			if (statement4 != null) {
				try {
					statement4.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}
			if (statement != null) {
				try {
					statement.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}
			if (statement12 != null) {
				try {
					statement12.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}
			if (statement13 != null) {
				try {
					statement13.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}
			if (statement14 != null) {
				try {
					statement14.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}

			if (connexion != null) {
				try {
					connexion.close();
					System.out.println("Fermeture du connection");
				} catch (SQLException ignore) {
				}
			}
		}
	}
}
