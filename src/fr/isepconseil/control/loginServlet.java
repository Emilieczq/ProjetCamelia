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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String login = request.getParameter("login"); // login => email
		String password = request.getParameter("password");
		response.setContentType("text/html");

		String info = null;
		if ((login == null || "".equals(login))||(password == null || "".equals(password))) {
			info = "La connexion a échoué, merci d'essayer de nouveau";
			System.out.println("login ou passworld vide"); // to be deleted
		}
		if (info == null) {
			User user = new User();
			user.setEmail(login);
			user.setPassword(password);
			request.setAttribute("user", user);
			UserDAOI userDAO = new UserDAOI();

			try {
				if (userDAO.findLogin(user)) {
					System.out.println("right login and password"); // to be deleted
					// check the user is teacher or student or old student
					if (userDAO.defineType(user)=="Professeur") {
						Professeur professeur = new Professeur();
						userDAO.setProfesseur(user, professeur);
						professeur = userDAO.getProfesseur();
						session.setAttribute("professeur", professeur); //session
						
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProfilPourProf.jsp");
						dispatcher.forward(request, response);
					} else {
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/recherchePourEleve.jsp");
						dispatcher.forward(request, response);
					}

				} else {
					user = new User(); // clear all the information in JavaBean
					System.out.println("wrong login or wrong password"); // to be deleted
					info="La connexion a échoué, merci d'essayer de nouveau";
					request.setAttribute("info", info);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("info", info);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}


}