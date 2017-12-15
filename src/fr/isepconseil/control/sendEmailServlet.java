package fr.isepconseil.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class sendEmailServlet
 */
@WebServlet("/sendEmailServlet")
public class sendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // need to download MySQL JDBC jar
    static final String DB_URL = "jdbc:mysql://localhost:3306/Camelia"; // correct the project name

    static final String USER = "root"; // your server user name
    static final String PASSWORD = "123456"; // your password
    
    private String pass = null;
    
	public sendEmailServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();	
		
		if (checkEmail(email)) {
			out.print("Votre mot de passe a bien envoyé à votre email !");
			sendEmail(email);
		} else {
			out.print("Cette adresse est invalide.");
		}

	}
	
	private void sendEmail(String to) { // (to do) send a link to reset password, not send the password directly
		// Sender's email ID needs to be mentioned
		String from = "cameliaprojet@gmail.com";
		final String username = "cameliaprojet@gmail.com";// change accordingly
		final String password = "cameliaprojet5";// change accordingly

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "pop.gmail.com");
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Votre mot de passe");

			// Send the actual HTML message, as big as you like
			message.setContent("<h3>Votre mot de passe est " + pass +".</h3>", "text/html");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private boolean checkEmail(String email) { // check if the entered email exists in the database
		try {
			// connect to mysql server
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Emails");
			while (rs.next()) {
				System.out.println(rs.getString("Email"));
				if (rs.getString("Email").equals(email)) {
					pass = rs.getString("Password");
					return true; // as soon as we found the same email in the database
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
		
	}

}
