package fr.isepconseil.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.isepconseil.dbc.DatabaseConnection;

@WebServlet("/RechercheServlet")
public class RechercheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseConnection dbc = null;
	private Connection connexion = null;
	private Statement statement = null, statement2 = null, statement3 = null;

	private ResultSet rset, rset2, rset3;

	public RechercheServlet() {
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

			String recherche = request.getParameter("search");
			System.out.println(recherche);

			response.setContentType("text/html");
			List<String> results = new ArrayList<String>();
			List<Integer> ids = new ArrayList<Integer>();
			List<String> types = new ArrayList<String>();

			if (recherche != null) {
				/*
				 * we can search any students by email or firstName+" "+lastName
				 * or lastName+" "+firstName ignore we write in upper case or in
				 * lower case no need for only firstName or only lastName,
				 * because they are included in this case and the following case
				 */
				rset = statement
						.executeQuery("select distinct Users.id_User from Users, Firm,Ecole,Echange,Stages where"
								+ "((email like '%"
								+ recherche
								+ "%' "
								+ "or lower(concat(firstName,'' '',lastName)) like '%"
								+ recherche
								+ "%'"
								+ "or lower(concat(lastName,'' '',firstName)) like '%"
								+ recherche
								+ "%')"
								+ "or lower(Firm.fname) like '%"
								+ recherche
								+ "%'"
								+ "or lower(Ecole.Ename) like '%"
								+ recherche
								+ "%'"
								+ "or lower(Firm.fpays) like '%"
								+ recherche
								+ "%'"
								+ "or lower(Ecole.ePays) like '%"
								+ recherche
								+ "%'"
								+ "and Users.id_User=Stages.id_User and Users.id_User=Echange.id_User "
								+ "and Stages.id_Firm = Firm.id_Firm and Echange.id_Ecole=Ecole.id_Ecole"
								+ ");");
				System.out.println("*******1");

				while (rset.next()) {
					ids.add(rset.getInt("id_User"));
				}
				for (int i : ids) {
					rset3 = statement3
							.executeQuery("select * from Users where id_User="
									+ i + ";");
					if (rset3.next()) {
						results.add(rset3.getString("firstName") + " "
								+ rset3.getString("lastName"));

						rset2 = statement2
								.executeQuery("select * from Students where id_User="
										+ i + ";");
						if (rset2.next()) {
							types.add("student");
						} else {
							types.add("teacher");
						}
					}
				}
			}

			request.setAttribute("results", results);
			request.setAttribute("ids", ids);
			request.setAttribute("types", types);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/resarchResult.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			System.out.println("Exception declenchee");
			e.printStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
					System.out.println("Fermeture du resultat");
				} catch (SQLException ignore) {
				}
			}
			if (rset2 != null) {
				try {
					rset2.close();
					System.out.println("Fermeture du resultat");
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
		}
	}
}
