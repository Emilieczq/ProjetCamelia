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

import fr.isepconseil.dbc.DatabaseConnection;
import fr.isepconseil.vo.Etudiant;

@WebServlet("/EleveContactServlet")
public class EleveContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseConnection dbc = null;
	private Connection connexion = null;
	private Statement statement1 = null;
	private Statement statement2 = null;
	private ResultSet resultat;

	public EleveContactServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			dbc = new DatabaseConnection();
			connexion = dbc.getConnection();
			statement1 = connexion.createStatement();
			statement2 = connexion.createStatement();

			String profName = request.getParameter("profName");
			String but = request.getParameter("but");

			// retrieve session to get id studend
			Etudiant etudiant = (Etudiant) request.getSession().getAttribute(
					"etudiant");
			int idStudent = etudiant.getIdEtudiant();
			
			
			//add the request to the RDV table with the appropriate teacher, student and subject
			if (profName != null && but != null) {
				resultat = statement1
						.executeQuery("SELECT id_Teacher from Camelia.Teachers where id_User = (select id_User from Camelia.Users where concat(firstName,' ',lastName)='"
								+ profName + "');");
				while (resultat.next()) {
					int idTeacher = resultat.getInt("id_Teacher");
					statement2
							.executeUpdate("Insert into RDV(id_Teacher, id_Student,subject) values ('"
									+ idTeacher
									+ "', '"
									+ idStudent
									+ "','"
									+ but + "');");
				}
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/ContactSuccess.html");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
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
