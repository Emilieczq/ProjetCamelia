package fr.isepconseil.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.isepconseil.dao.UserDAOI;
import fr.isepconseil.vo.Etudiant;
import fr.isepconseil.vo.Professeur;
import fr.isepconseil.vo.User;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String login = request.getParameter("login"); // login => email
		String password = request.getParameter("password");
		response.setContentType("text/html");

		String info = null;
		if ((login == null || "".equals(login))
				|| (password == null || "".equals(password))) {
			info = "La connexion a échoué, merci d'essayer de nouveau";

		}
		if (info == null) {
			UserDAOI userDAO = new UserDAOI();
			User user = new User();
			user.setEmail(login);
			user.setPassword(password);

			try {
				if (userDAO.findLogin(user)) { // make sure that the user is
												// professor
					if (userDAO.defineType(user) == "Professeur") {
						Professeur professeur = new Professeur();
						userDAO.setProfesseur(user, professeur);
						professeur = userDAO.getProfesseur();
						user.setType("teacher");
						session.setAttribute("professeur", professeur);

						RequestDispatcher dispatcher = getServletContext()
								.getRequestDispatcher("/recherchePourProf.jsp");
						dispatcher.forward(request, response);
					} else { // make sure that the user is student
						Etudiant etudiant = new Etudiant();
						userDAO.setEtudiant(user, etudiant);
						etudiant = userDAO.getEtudiant();
						user.setType("student");
						session.setAttribute("etudiant", etudiant);

						RequestDispatcher dispatcher = getServletContext()
								.getRequestDispatcher("/recherchePourEleve.jsp");
						dispatcher.forward(request, response);
					}
					session.setAttribute("user", user);

				} else {
					info = "La connexion a échoué, merci d'essayer de nouveau";
					request.setAttribute("info", info);
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("info", info);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}

}
