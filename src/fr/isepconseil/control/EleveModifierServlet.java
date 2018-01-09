package fr.isepconseil.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
			statement1 = connexion.createStatement();
			HttpSession session = request.getSession(true);



			PrintWriter out = response.getWriter();
/*			String mNumero = request.getParameter("numero");
*/			String mPromo = request.getParameter("promotion");
			String mParcours = request.getParameter("parcours");
			String mToeic = request.getParameter("toeic");
			String sAlternance = request.getParameter("alternance");
			System.out.println("Alternance " + sAlternance);
			int iAlternance = 1;
			if (sAlternance == "alternance" || sAlternance.equals("alternance")){
				iAlternance = 0;
			}
			User user = new User();
			Etudiant etudiant = (Etudiant)request.getSession().getAttribute("etudiant");
			String iemail = etudiant.getEmail();
			String ipassword = user.getPassword();


			if ( mPromo != null ||mParcours != null || mToeic != null ) {
				resultat = statement1.executeQuery("select id_User from Users where email = '"+iemail+"';");
				System.out.println("Marche1"); //test a delete
				while (resultat.next()){
					int idUser = resultat.getInt( "id_User" );
					modif = statement.executeUpdate( "Update Students set parcours = '"+mParcours+"', studyyear = '"+mPromo+"', alternance ='"+iAlternance+"', toeic = '"+mToeic+"' where id_User = '"+idUser+"';");	
					user.setEmail(iemail);
					user.setPassword(ipassword);
					request.setAttribute("user", user);
					UserDAOI userDAO = new UserDAOI();
					Etudiant etudiant2 = new Etudiant();
					userDAO.setEtudiant(user, etudiant2);
					etudiant = userDAO.getEtudiant();
					session.setAttribute("etudiant", etudiant);				
				}
			
					        			
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProfilPourEleve.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception declenchee");
			e.printStackTrace();
		}
	}

}