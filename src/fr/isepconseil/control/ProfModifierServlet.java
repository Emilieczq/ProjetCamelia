package fr.isepconseil.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
 * Servlet implementation class ProfModifierServlet
 */
@WebServlet("/ProfModifierServlet")
public class ProfModifierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseConnection dbc = null;
	private Connection connexion = null;
	private Statement statement = null;   
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
			fnameRecherche = connexion.createStatement();
			
			PrintWriter out = response.getWriter();
			String recherche = request.getParameter("search");
			System.out.println(recherche);


			if (recherche != null ) {
				resultatFnameRecherche = fnameRecherche.executeQuery("select firstName from Users where email = '"+recherche+"';");
				while (resultatFnameRecherche.next()){
					String fnameRech = resultatFnameRecherche.getString( "firstName" );
					System.out.println(fnameRech);
					request.setAttribute("fnameRech", fnameRech );
				}
				
				resultatLnameRecherche = lnameRecherche.executeQuery("select lastName from Users where email = '"+recherche+"';");
				while (resultatLnameRecherche.next()){
					String lnameRech = resultatLnameRecherche.getString( "lastName" );
					System.out.println(lnameRech);
					request.setAttribute("lnameRech", lnameRech );
				}
				
				resultatIdRecherche = idRecherche.executeQuery("select id_User from Users where email = '"+recherche+"';");
				while (resultatIdRecherche.next()){
					String idRech = resultatIdRecherche.getString( "id_User" );
					System.out.println(idRech);
					request.setAttribute("idRech", idRech );
				}

			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/resarchResult.jsp"); //a modifier, page de resultat
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception declenchee");
			e.printStackTrace();
		}
		finally {

			if ( resultatFnameRecherche != null ) {
				try {

					resultatFnameRecherche.close();
					System.out.println("Fermeture du resultat");

				} catch ( SQLException ignore ) {
				}

			}
			if ( fnameRecherche != null ) {
				try {

					fnameRecherche.close();
					System.out.println("Fermeture du statement");

				} catch ( SQLException ignore ) {
				}
			}
		}
	}

}
