package fr.isepconseil.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
	private Statement statementbis = null;
	private Statement statementidstud = null;
	private Statement statementcheckstage = null;
	private ResultSet resultat = null;
	private ResultSet resultatstage = null;
	private ResultSet checkstage = null;
	private int modif;
	private int stagemodif;
	private int stagemodif2;

	public EleveModifierServlet() {
		super();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			dbc = new DatabaseConnection();
			connexion = dbc.getConnection();
			statement = connexion.createStatement();
			statementbis = connexion.createStatement();
			statementidstud = connexion.createStatement();
			statementcheckstage = connexion.createStatement();

			HttpSession session = request.getSession(true);

			
			//Info profil eleve
			
			String mPromo = request.getParameter("promotion");
			String mParcours = request.getParameter("parcours");
			String mToeic = request.getParameter("toeic");
			String sAlternance = request.getParameter("alternance");
			System.out.println("Alternance " + sAlternance);
			User user = new User();
			Etudiant etudiant = (Etudiant) request.getSession().getAttribute("etudiant");
			String iemail = etudiant.getEmail();
			int iduser = user.getId();
			
			//Info Stage
			// ATTENTION SALARY A ETE CHANGE DANS LA BDD A VARCHAR MAIS A ESSAYER FLOAT ET INT
			// AVEC PARSEINT OU PARSEFLOAT SUR LE STRING
			
			String s1Firm = request.getParameter("HEntreprises"); 
			String s1Job = request.getParameter("HPoste");
			String s1Town =  request.getParameter("Hville2");
			String s1Country = request.getParameter("hPays2");
			String s1Salaries = request.getParameter("salaires");
			String s1Start = request.getParameter("debut3");
			String s1End = request.getParameter("fin3");
			
			String s2Firm = request.getParameter("HEntreprises1");
			String s2Job = request.getParameter("HPoste1");
			String s2Town =  request.getParameter("Hville3");
			String s2Country = request.getParameter("hPays3");
			String s2Salaries = request.getParameter("salaires1");
			String s2Start = request.getParameter("debut4");
			String s2End = request.getParameter("fin4");

			System.out.println("Debut du stage de a3" + s2Start);
			
			
			// Info Echange
			
			String e1Start = request.getParameter("debut1");
			String e1End = request.getParameter("fin1");
			
			String e2Start = request.getParameter("debut2");
			String e2End = request.getParameter("fin2");
			

			resultat = statement.executeQuery("select id_User from Users where email = '" + iemail + "';");

			System.out.println("Marche1"); // test a delete
			while (resultat.next()) {
				int idUser = resultat.getInt("id_User");
				resultatstage = statementidstud.executeQuery("select id_User from Stages where id_User = '" + idUser + "';");

				if (mPromo != null) {

					modif = statementbis.executeUpdate(
							"Update Students set studyyear = '" + mPromo + "' where id_User = '" + idUser + "';"); 
					etudiant.setAnnee(mPromo);
				}
				if (mParcours != null) {

					modif = statementbis.executeUpdate(
							"Update Students set parcours = '" + mParcours + "' where id_User = '" + idUser + "';");
					etudiant.setParcours(mParcours); 
				}
				if (mToeic != null && !mToeic.equals("")) {

					modif = statementbis.executeUpdate(
							"Update Students set toeic = '" + mToeic + "' where id_User = '" + idUser + "';");
					etudiant.setToeic(Integer.parseInt(mToeic));
				}
				if (sAlternance.equals("1")|| sAlternance.equals("0")){

					modif = statementbis.executeUpdate(
							"Update Students set alternance = '" + sAlternance + "' where id_User = '" + idUser + "';");
					etudiant.setAlternance(Integer.parseInt(sAlternance));
				}
				
				
				// Modifier la BDD      id_User int not null, dans table students à la place de id_student
				// et     foreign key (id_User) references Users(id_User),

				if(s1Firm !=null && s1Job !=null && s1Salaries != null){
					stagemodif = statementcheckstage.executeUpdate("Update Stages set sFirm = '" + s1Firm + "',sJob = '" + s1Job + "',sSalary = '" + s1Salaries + "' where id_User = '" + idUser + "' and sYear =  'A2';");
			
				}
				if (s2Firm !=null && s2Job !=null && s2Salaries != null){
					stagemodif2 = statementcheckstage.executeUpdate("Update Stages set sFirm = '" + s2Firm + "',sJob = '" + s2Job + "',sSalary = '" + s2Salaries + "' where id_User = '" + idUser + "' and sYear =  'A3';");
				}
				
					
//					if (idStudent != null){
//						if (s1annee != null){
//							modif = statementbis.executeUpdate(
//									"Insert into Stages(sYear) values ('" + s1annee + "');
//						}
//						if (s1Firm != null){
//							modif = statementbis.executeUpdate(
//									"Update Stages set sFirm = '" + s1Firm + "' where id_Student = '" + idStudent + "';");
//						}
//						if (s1Job != null){
//							modif = statementbis.executeUpdate(
//									"Update Stages set sJob = '" + s1Job + "' where id_Student = '" + idStudent + "';");
//						}
//						
//					}
					

				

			}


			user.setEmail(iemail);

			session.setAttribute("user", user); // => user in session, not request (état faux dans userDao, mais corrigé à mon coté
			/*
			 * inutile à utiliser userDao. userDao est seulement utilisé dans login
			 * une fois user et etudiant ou professeur sont créé, si on change des données, on changes sur l'objet directement, pas créer un nouveau
			 */
			/*			UserDAOI userDAO = new UserDAOI();
			Etudiant etudiant2 = new Etudiant();
			userDAO.setEtudiant(user, etudiant2);
			etudiant = userDAO.getEtudiant();*/

			session.setAttribute("etudiant", etudiant); // => comme tu as déjà modifié etudiant, quand tu utilises setAttribute, il va remplacé ce "nouveau" etudiant






			
			
			
			

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

			if (statement != null) {
				try {
					statement.close();
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
