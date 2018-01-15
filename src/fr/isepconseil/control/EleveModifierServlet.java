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

/**
 * Servlet implementation class EleveModifierServlet
 */
@WebServlet("/EleveModifierServlet")
public class EleveModifierServlet extends HttpServlet {

	// creation of statement and resultset for sql queries
	private static final long serialVersionUID = 1L;
	private DatabaseConnection dbc = null;
	private Connection connexion = null;
	private Statement statement = null, statement2 = null, statement3 = null,
			statement4 = null, statement5 = null, statement6 = null,
			statement7 = null, statement8 = null, statement9 = null,
			statement10 = null, statement11 = null;
	private Statement statementbis = null;
	private Statement statementcheckstage = null;
	private ResultSet resultat = null, resultat2 = null, resultat3 = null,
			resultat4 = null, resultat5 = null, resultat6 = null,
			resultat7 = null, resultat8 = null, resultat9 = null,
			resultat10 = null, resultat11 = null;
	private ResultSet resultatstage = null;

	public EleveModifierServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			dbc = new DatabaseConnection();
			connexion = dbc.getConnection();
			statement = connexion.createStatement();
			statement2 = connexion.createStatement();
			statement3 = connexion.createStatement();
			statement4 = connexion.createStatement();
			statement5 = connexion.createStatement();
			statement6 = connexion.createStatement();
			statement7 = connexion.createStatement();
			statement8 = connexion.createStatement();
			statement9 = connexion.createStatement();
			statement10 = connexion.createStatement();
			statement11 = connexion.createStatement();

			statementbis = connexion.createStatement();
			statementcheckstage = connexion.createStatement();

			// retrieve session
			HttpSession session = request.getSession(true);

			// Profil eleve information entered in the form
			String mPromo = request.getParameter("promotion");
			String mParcours = request.getParameter("parcours");
			String mToeic = request.getParameter("toeic");
			String sAlternance = request.getParameter("alternance");

			// get the ID of the logged in user
			User user = (User) session.getAttribute("user");
			Etudiant etudiant = (Etudiant) session.getAttribute("etudiant");
			int idUser = user.getId();

			// Stage information
			String s1Firm = request.getParameter("s1Firm");
			String s1Job = request.getParameter("s1Job");
			String s1Town = request.getParameter("s1Town");
			String s1Country = request.getParameter("s1Country");
			String s1Salary = request.getParameter("s1Salary");
			String s1Start = request.getParameter("s1Start");
			String s1End = request.getParameter("s1End");
			String competences1 = request.getParameter("competences1");
			String mission1 = request.getParameter("mission1");

			String s2Firm = request.getParameter("s2Firm");
			String s2Job = request.getParameter("s2Job");
			String s2Town = request.getParameter("s2Town");
			String s2Country = request.getParameter("s2Country");
			String s2Salary = request.getParameter("s2Salary");
			String s2Start = request.getParameter("s2Start");
			String s2End = request.getParameter("s2End");
			String competences2 = request.getParameter("competences2");
			String mission2 = request.getParameter("mission2");

			// Echange information
			String e1Ecole = request.getParameter("e1Etablissement");
			String e1Ville = request.getParameter("e1Town");
			String e1Pays = request.getParameter("e1Country");
			String e1Start = request.getParameter("e1Start");
			String e1End = request.getParameter("e1End");

			/**
			 * update each parameters of the students logged in if he entered
			 * something in the assigned field
			 */

			if (mPromo != null) {
				statementbis.executeUpdate("Update Students set studyyear = '"
						+ mPromo + "' where id_User = '" + idUser + "';");
				etudiant.setAnnee(mPromo);
			}
			if (mParcours != null) {
				statementbis.executeUpdate("Update Students set parcours = '"
						+ mParcours + "' where id_User = '" + idUser + "';");
				etudiant.setParcours(mParcours.replace("''", "'"));
			}
			if (mToeic != null && !mToeic.equals("")) {
				statementbis.executeUpdate("Update Students set toeic = '"
						+ mToeic + "' where id_User = '" + idUser + "';");
				etudiant.setToeic(Integer.parseInt(mToeic));
			}
			if (sAlternance.equals("1") || sAlternance.equals("0")) {
				statementbis.executeUpdate("Update Students set alternance = '"
						+ sAlternance + "' where id_User = '" + idUser + "';");
				etudiant.setAlternance(Integer.parseInt(sAlternance));
			}

			/**
			 * check if the school exists already in the database: if yes, get
			 * the id_Ecole and use it in the table Stages; if not, create new
			 * row in the table Ecole and get the new id_Ecole to use in the
			 * table Echange
			 */

			int id_Ecole = 0;
			if (e1Ecole != "" && e1Ville != "" && e1Pays != "" && e1Start != ""
					&& e1End != "") {
				resultat9 = statement9
						.executeQuery("select * from Ecole where Ename= '"
								+ e1Ecole + "' && eVille= '" + e1Ville
								+ "' && ePays='" + e1Pays + "';");
				if (resultat9.next()) {
					id_Ecole = resultat9.getInt("id_Ecole");
				} else {
					statement10
							.executeUpdate("insert into Ecole(Ename, eVille, ePays) values('"
									+ e1Ecole
									+ "','"
									+ e1Ville
									+ "','"
									+ e1Pays + "');");
					resultat10 = statement10
							.executeQuery("select * from Ecole where Ename= '"
									+ e1Ecole + "' && eVille= '" + e1Ville
									+ "' && ePays='" + e1Pays + "';");
					if (resultat10.next()) {
						id_Ecole = resultat10.getInt("id_Ecole");
					}
				}
				resultat11 = statement11
						.executeQuery("select * from Echange where id_User= '"
								+ idUser + "' && eYear= 'A3';");

				if (resultat11.next()) {
					int id_Echange = resultat11.getInt("id_Echange");
					statementcheckstage
							.executeUpdate("update Echange set eStart='"
									+ e1Start + "',eEnd='" + e1End
									+ "' where id_Echange ='" + id_Echange
									+ "';");
				} else {
					statementcheckstage
							.executeUpdate("insert into Echange(id_Ecole,id_User,eYear, eStart, eEnd) values ('"
									+ id_Ecole
									+ "','"
									+ idUser
									+ "','A3','"
									+ e1Start + "','" + e1End + "');");
				}
			}

			/**
			 * check if the firm exists already in the database: if yes, get the
			 * id_Firm and use it in the table Stages; if not, create new row in
			 * the table Firm and get the new id_Firm to use in the table Stages
			 */
			int id_Firm1 = 0, id_Firm2 = 0;

			if (s1Firm != "" && s1Job != "" && s1Salary != "" && s1Start != ""
					&& s1End != "" && competences1 != "" && mission1 != "") {
				resultat2 = statement2
						.executeQuery("select * from Firm where fname= '"
								+ s1Firm + "' && fville= '" + s1Town
								+ "' && fpays='" + s1Country + "';");
				if (resultat2.next()) {
					id_Firm1 = resultat2.getInt("id_Firm");
				} else {
					statement3
							.executeUpdate("insert into Firm(fname, fville, fpays) values('"
									+ s1Firm
									+ "','"
									+ s1Town
									+ "','"
									+ s1Country + "');");
					resultat5 = statement5
							.executeQuery("select * from Firm where fname= '"
									+ s1Firm + "' && fville= '" + s1Town
									+ "' && fpays='" + s1Country + "';");
					if (resultat5.next()) {
						id_Firm1 = resultat5.getInt("id_Firm");
					}
				}
				resultat7 = statement7
						.executeQuery("select * from Stages where id_User= '"
								+ idUser + "' && sYear= 'A2';");
				if (resultat7.next()) {
					int id_Stage = resultat7.getInt("id_Stage");
					statementcheckstage
							.executeUpdate("update Stages set sJob='" + s1Job
									+ "', sSalary='" + s1Salary + "',sStart='"
									+ s1Start + "',sEnd='" + s1End
									+ "', competences='" + competences1
									+ "', mission='" + mission1
									+ "' where id_Stage ='" + id_Stage + "';");
				} else {
					statementcheckstage
							.executeUpdate("insert into Stages(sJob,id_Firm,sSalary,id_User,sYear, sStart, sEnd, competences, mission) values ('"
									+ s1Job
									+ "','"
									+ id_Firm1
									+ "','"
									+ s1Salary
									+ "','"
									+ idUser
									+ "','A2','"
									+ s1Start
									+ "','"
									+ s1End
									+ "','"
									+ competences1 + "','" + mission1 + "');");
				}
			}
			if (s2Firm != "" && s2Job != "" && s2Salary != "" && s2Start != ""
					&& s2End != "" && competences2 != "" && mission2 != "") {
				resultat4 = statement4
						.executeQuery("select * from Firm where fname= '"
								+ s2Firm + "' && fville= '" + s2Town
								+ "' && fpays='" + s2Country + "';");
				if (resultat4.next()) {
					id_Firm2 = resultat4.getInt("id_Firm");
				} else {
					statement6
							.executeUpdate("insert into Firm(fname, fville, fpays) values('"
									+ s2Firm
									+ "','"
									+ s2Town
									+ "','"
									+ s2Country + "');");
					resultat6 = statement6
							.executeQuery("select * from Firm where fname= '"
									+ s2Firm + "' && fville= '" + s2Town
									+ "' && fpays='" + s2Country + "';");
					if (resultat6.next()) {
						id_Firm2 = resultat6.getInt("id_Firm");
					}
				}
				resultat8 = statement8
						.executeQuery("select * from Stages where id_User= '"
								+ idUser + "' && sYear= 'A3';");
				if (resultat8.next()) {
					int id_Stage = resultat8.getInt("id_Stage");
					statementcheckstage
							.executeUpdate("update Stages set sJob='" + s2Job
									+ "', sSalary='" + s2Salary + "',sStart='"
									+ s2Start + "',sEnd='" + s2End
									+ "', competences='" + competences2
									+ "', mission='" + mission2
									+ "' where id_Stage ='" + id_Stage + "';");
				} else {
					statementcheckstage
							.executeUpdate("insert into Stages(sJob,id_Firm,sSalary,id_User,sYear,sStart, sEnd,competences, mission) values ('"
									+ s2Job
									+ "','"
									+ id_Firm2
									+ "','"
									+ s2Salary
									+ "','"
									+ idUser
									+ "','A3','"
									+ s2Start
									+ "','"
									+ s2End
									+ "','"
									+ competences2 + "','" + mission2 + "');");
				}
			}
			session.setAttribute("user", user);
			session.setAttribute("etudiant", etudiant);

			// Redirect to the profil eleve page
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/ProfilPourEleve.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out
					.println("Exception declenchee dans EleveModifierServlet");
			e.printStackTrace();
		} finally {
			if (resultat != null) {
				try {
					resultat.close();
					System.out.println("Fermeture du resulset");
				} catch (SQLException ignore) {
				}
			}
			if (resultat2 != null) {
				try {
					resultat2.close();
					System.out.println("Fermeture du resulset");
				} catch (SQLException ignore) {
				}
			}
			if (resultat3 != null) {
				try {
					resultat3.close();
					System.out.println("Fermeture du resulset");
				} catch (SQLException ignore) {
				}
			}
			if (resultat4 != null) {
				try {
					resultat4.close();
					System.out.println("Fermeture du resulset");
				} catch (SQLException ignore) {
				}
			}
			if (resultat5 != null) {
				try {
					resultat5.close();
					System.out.println("Fermeture du resulset");
				} catch (SQLException ignore) {
				}
			}
			if (resultat6 != null) {
				try {
					resultat6.close();
					System.out.println("Fermeture du resulset");
				} catch (SQLException ignore) {
				}
			}
			if (resultat7 != null) {
				try {
					resultat7.close();
					System.out.println("Fermeture du resulset");
				} catch (SQLException ignore) {
				}
			}
			if (resultat8 != null) {
				try {
					resultat8.close();
					System.out.println("Fermeture du resulset");
				} catch (SQLException ignore) {
				}
			}
			if (resultat9 != null) {
				try {
					resultat9.close();
					System.out.println("Fermeture du resulset");
				} catch (SQLException ignore) {
				}
			}
			if (resultat10 != null) {
				try {
					resultat11.close();
					System.out.println("Fermeture du resulset");
				} catch (SQLException ignore) {
				}
			}
			if (resultat11 != null) {
				try {
					resultat11.close();
					System.out.println("Fermeture du resulset");
				} catch (SQLException ignore) {
				}
			}
			if (resultatstage != null) {
				try {
					resultatstage.close();
					System.out.println("Fermeture du resulset");
				} catch (SQLException ignore) {
				}
			}
			if (statementbis != null) {
				try {
					statementbis.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}
			if (statementcheckstage != null) {
				try {
					statementcheckstage.close();
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
			if (statement5 != null) {
				try {
					statement5.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}
			if (statement6 != null) {
				try {
					statement6.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}
			if (statement7 != null) {
				try {
					statement7.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}
			if (statement8 != null) {
				try {
					statement8.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}

			if (statement9 != null) {
				try {
					statement9.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}
			if (statement10 != null) {
				try {
					statement10.close();
					System.out.println("Fermeture du statement");
				} catch (SQLException ignore) {
				}
			}
			if (statement11 != null) {
				try {
					statement11.close();
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
