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

/**
 * Servlet implementation class ProfModifierServlet
 */
@WebServlet("/ProfModifierServlet")
public class ProfModifierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseConnection dbc = null;
	private Connection connexion = null;
	private Statement statement = null;   
	private Statement statementbis = null;
	private ResultSet resultat = null;
	private int modif;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfModifierServlet() {
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
			statementbis = connexion.createStatement();

			HttpSession session = request.getSession(true);

			String pPoste = request.getParameter("poste");
			String pBureau = request.getParameter("bureau");
			String pTel = request.getParameter("tel");
			System.out.println(pPoste);
			System.out.println(pTel);
			System.out.println(pBureau);


			
			User user = new User();
			Professeur professeur = (Professeur) request.getSession().getAttribute("professeur");
			String iemail = professeur.getEmail();


			resultat = statement.executeQuery("select id_User from Users where email = '" + iemail + "';");

			System.out.println("Marche1"); // test a delete
			
			while (resultat.next()) {
				if (pPoste != null && !pPoste.equals("")) {
					int idUser = resultat.getInt("id_User");
					modif = statementbis.executeUpdate(
							"Update Teachers set poste = '" + pPoste + "' where id_User = '" + idUser + "';"); 
					professeur.setPoste(pPoste);
				}
				if (pBureau != null && !pBureau.equals("")) {
					int idUser = resultat.getInt("id_User");
					modif = statementbis.executeUpdate(
							"Update Teachers set office = '" + pBureau + "' where id_User = '" + idUser + "';");
					professeur.setBureau(pBureau); 
				}
				if (pTel != null && !pTel.equals("")) {
					int idUser = resultat.getInt("id_User");
					modif = statementbis.executeUpdate(
							"Update Teachers set phone = '" + pTel + "' where id_User = '" + idUser + "';");
					professeur.setTel(pTel);
				}
			}
			
			user.setEmail(iemail);
			session.setAttribute("user", user); 
			session.setAttribute("professeur", professeur);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProfilPourProf.jsp"); 
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception declenchee");
			e.printStackTrace();
		}
		finally {

			if ( resultat != null ) {
				try {

					statement.close();
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
			if ( statementbis != null ) {
				try {

					statementbis.close();
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
