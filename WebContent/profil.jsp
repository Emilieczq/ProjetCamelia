<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import=" fr.isepconseil.dbc.DatabaseConnection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%  
	int id =  Integer.parseInt(request.getParameter("id"));  // get attribute by URL
	DatabaseConnection dbc = null;
	Connection connexion = null;
	Statement statement = null;
	
	ResultSet rset = null;
	
	try{
		dbc = new DatabaseConnection();
		connexion = dbc.getConnection();
		statement = connexion.createStatement();
	
		rset = statement.executeQuery("select * from Users, Students where Users.id_User=" + id + " and Students.id_User=" + id+ ";");
		
		while (rset.next()) {
 %>
 <p><%=id %></p>
 <table>
 	<tr>
 		<td>Nom</td>
 		<td><%=rset.getString("lastName") %></td>
 	</tr>
 	<tr>
 		<td>Prénom</td>
 		<td><%=rset.getString("firstName") %></td>
 	</tr>
 	<tr>
 		<td>Email</td>
 		<td><%=rset.getString("email") %></td>
 	</tr>
 	<tr>
 		<td></td>
 		<td></td>
 	</tr>
 	<tr>
 		<td></td>
 		<td></td>
 	</tr>
 	<tr>
 		<td></td>
 		<td></td>
 	</tr>
 	<tr>
 		<td></td>
 		<td></td>
 	</tr>
 	<tr>
 		<td></td>
 		<td></td>
 	</tr>
 </table>
 <%
		}
	} catch (Exception e) {
		System.out.println("Exception declenchee");
		e.printStackTrace();
	}
	finally {
	
		if ( rset != null ) {
			try {
				rset.close();
				System.out.println("Fermeture du resultat");
			} catch ( SQLException ignore ) {
			}
		}
		if ( statement != null ) {
			try {
				statement.close();
				System.out.println("Fermeture du statement");
			} catch ( SQLException ignore ) {
			}
		}
	}

 
 
 %>
</body>
</html>