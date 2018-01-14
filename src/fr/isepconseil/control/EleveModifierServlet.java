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
	private Statement statement = null, statement2=null,statement3=null,statement4=null,statement5=null,statement6=null,statement7=null,statement8=null;
	private Statement statementbis = null;
//	private Statement statementidstud = null;
	private Statement statementcheckstage = null;
	private ResultSet resultat = null,resultat2 =null, resultat3=null,resultat4=null,resultat5 =null, resultat6=null,resultat7=null,resultat8=null;
	private ResultSet resultatstage = null;
//	private ResultSet checkstage = null;
	private int modif;
	private int stagemodif;
	private int stagemodif2;

	public EleveModifierServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			statementbis = connexion.createStatement();
//			statementidstud = connexion.createStatement();
			statementcheckstage = connexion.createStatement();

			HttpSession session = request.getSession(true);

			
			//Info profil eleve
			
			String mPromo = request.getParameter("promotion");
			String mParcours = request.getParameter("parcours");
			String mToeic = request.getParameter("toeic");
			String sAlternance = request.getParameter("alternance");
			
			User user = (User)session.getAttribute("user");
			Etudiant etudiant = (Etudiant) session.getAttribute("etudiant");
			int idUser = user.getId();
			
			//Info Stage
			// ATTENTION SALARY A ETE CHANGE DANS LA BDD A VARCHAR MAIS A ESSAYER FLOAT ET INT
			// AVEC PARSEINT OU PARSEFLOAT SUR LE STRING
			
			String s1Firm = request.getParameter("s1Firm"); 
			String s1Job = request.getParameter("s1Job");
			String s1Town =  request.getParameter("s1Town");
			String s1Country = request.getParameter("s1Country");
			String s1Salary = request.getParameter("s1Salary");
			String s1Start = request.getParameter("s1Start");
			String s1End = request.getParameter("s1End");
			String competences1 = request.getParameter("competences1");
			String mission1 = request.getParameter("mission1");
			
			String s2Firm = request.getParameter("s2Firm");
			String s2Job = request.getParameter("s2Job");
			String s2Town =  request.getParameter("s2Town");
			String s2Country = request.getParameter("s2Country");
			String s2Salary = request.getParameter("s2Salary");
			String s2Start = request.getParameter("s2Start");
			String s2End = request.getParameter("s2End");
			String competences2 = request.getParameter("competences2");
			String mission2 = request.getParameter("mission2");

			
			
			// Info Echange
//			
//			String e1Start = request.getParameter("debut1");
//			String e1End = request.getParameter("fin1");
//			
//			String e2Start = request.getParameter("debut2");
//			String e2End = request.getParameter("fin2");
			

//			resultat = statement.executeQuery("select id_User from Users where email = '" + iemail + "';");


			if (mPromo != null) {
				modif = statementbis.executeUpdate(
						"Update Students set studyyear = '" + mPromo + "' where id_User = '" + idUser + "';"); 
				etudiant.setAnnee(mPromo);
			}
			if (mParcours != null) {
				modif = statementbis.executeUpdate(
						"Update Students set parcours = '" + mParcours + "' where id_User = '" + idUser + "';");
				etudiant.setParcours(mParcours.replace("''", "'")); 
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
			
			/**
			 * check if the firm exists already in the database:
			 * if yes, get the id_Firm add use it in the table Stages;
			 * if not, create new row in the table Firm and get the new id_Firm to use in the table Stages
			 */
			int id_Firm1=0, id_Firm2=0;
			
			if(s1Firm !="" && s1Job !="" && s1Salary != "" && s1Start !="" && s1End !="" && competences1!="" && mission1!=""){
				resultat2 = statement2.executeQuery("select * from Firm where fname= '" + s1Firm + "' && fville= '"+ s1Town + "' && fpays='"+s1Country+"';");
				System.out.println("-------------------------after resultat2----------");
				if(resultat2.next()) {
					id_Firm1 = resultat2.getInt("id_Firm");
					
					System.out.println("---------idFirm1=" + id_Firm1);
				}else {
					statement3.executeUpdate("insert into Firm(fname, fville, fpays) values('"+s1Firm+"','" + s1Town+"','" +s1Country+"');");
					System.out.println("-------------------------after resultat2 and 3 (insert)----------");
					resultat5 = statement5.executeQuery("select * from Firm where fname= '" + s1Firm + "' && fville= '"+ s1Town + "' && fpays='"+s1Country+"';");
					if(resultat5.next()) {
						id_Firm1 = resultat5.getInt("id_Firm");
						System.out.println("---------idFirm1=" + id_Firm1 +"------");
						System.out.println("-------------------------after resultat5 ----------");
					}
				}
				resultat7 =statement7.executeQuery("select * from Stages where id_User= '" + idUser + "' && sYear= 'A2';");
				if(resultat7.next()) {
					int id_Stage = resultat7.getInt("id_Stage");
					statementcheckstage.executeUpdate("update Stages set sJob='"+s1Job+"', sSalary='"+ s1Salary+"',sStart='"+ s1Start+"',sEnd='"+ s1End+"', competences='"
							+competences1+"', mission='" + mission1+"' where id_Stage ='" +id_Stage+"';");
				}else {
					statementcheckstage.executeUpdate(
							/**
							 * for now ignore date 
							 */
	//						"insert into Stages(sJob,id_Firm,sSalary,sStart,sEnd,id_User,sYear,competences, mission) values ('" + s1Job + "','"+id_Firm1+"','"+ s1Salary 
	//						+ "','" +s1Start + "','"+ s1End + "','"+idUser + "','A2','"+ competences1 +"','"+mission1+"');");
							"insert into Stages(sJob,id_Firm,sSalary,id_User,sYear, sStart, sEnd, competences, mission) values ('" + s1Job + "','"+id_Firm1+"','"+ s1Salary 
							+ "','"+idUser + "','A2','"+ s1Start +"','"+ s1End +"','"+ competences1 +"','"+mission1+"');");
					System.out.println("-------------------------after stagemodif (A2)----------");
				}
			}
			if(s2Firm !="" && s2Job !="" && s2Salary != "" && s2Start !="" && s2End !="" && competences2!="" && mission2!=""){
				resultat4 = statement4.executeQuery("select * from Firm where fname= '" + s2Firm+ "' && fville= '"+ s2Town + "' && fpays='"+s2Country+"';");
				System.out.println("-------------------------after resultat5----------");
				if(resultat4.next()) {
					id_Firm2= resultat4.getInt("id_Firm");
					System.out.println("---------idFirm2=" + id_Firm1 );
					System.out.println("-------------------------after resultat4 and 6 (update)----------");
				}else {
					statement6.executeUpdate("insert into Firm(fname, fville, fpays) values('"+s2Firm+"','" + s2Town+"','" +s2Country+"');");
					System.out.println("-------------------------after resultat4 and 5 (insert)----------");
					resultat6 = statement6.executeQuery("select * from Firm where fname= '" + s2Firm+ "' && fville= '"+ s2Town + "' && fpays='"+s2Country+"';");
					if(resultat6.next()) {
						id_Firm2 = resultat6.getInt("id_Firm");
						System.out.println("---------idFirm2=" + id_Firm2 +"------");
						System.out.println("-------------------------after resultat6 ----------");
					}
				}
				resultat8 =statement8.executeQuery("select * from Stages where id_User= '" + idUser + "' && sYear= 'A3';");
				if(resultat8.next()) {
					int id_Stage = resultat8.getInt("id_Stage");
					statementcheckstage.executeUpdate("update Stages set sJob='"+s2Job+"', sSalary='"+ s2Salary+"',sStart='"+ s2Start+"',sEnd='"+ s2End+"', competences='"
							+competences2+"', mission='" + mission2+"' where id_Stage ='" +id_Stage+"';");
				}else {
					statementcheckstage.executeUpdate(
							/**
							 * ignore date
							 */
	//						"insert into Stages(sJob,id_Firm,sSalary,sStart,sEnd,id_User,sYear,competences, mission) values ('" + s2Job + "','"+id_Firm2+"','"+ s2Salary 
	//						+ "','" +s2Start + "','"+ s2End + "','"+idUser + "','A3','"+ competences2 +"','"+mission2+"');");
							"insert into Stages(sJob,id_Firm,sSalary,id_User,sYear,sStart, sEnd,competences, mission) values ('" + s2Job + "','"+id_Firm2+"','"+ s2Salary 
							+ "','"+idUser + "','A3','"+ s2Start +"','"+ s2End +"','"+ competences2 +"','"+mission2+"');");
					System.out.println("-------------------------after stagemodif (A3)----------");
				}
			}
				
			System.out.println("--------test user--------" + user.toString());
			System.out.println("---------test etudiant-------" + etudiant.toString());
			session.setAttribute("user", user);
			session.setAttribute("etudiant", etudiant);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProfilPourEleve.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Exception declenchee 2");
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
