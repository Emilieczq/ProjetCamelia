<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import=" fr.isepconseil.dbc.DatabaseConnection"%>
<%@ page import="fr.isepconseil.vo.User" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%  
	int id =  Integer.parseInt(request.getParameter("id"));  // get attribute by URL
	String type = request.getParameter("type"); // get attribute by URL
	DatabaseConnection dbc = null;
	Connection connexion = null;
	Statement statement = null, statement2=null;
	
	ResultSet rset = null, rset2=null;
	
	try{
		dbc = new DatabaseConnection();
		connexion = dbc.getConnection();
		statement = connexion.createStatement();
		statement2 = connexion.createStatement();
		if(type.equals("student")){
			rset = statement.executeQuery("select * from Users, Students where Users.id_User=" + id + " and Students.id_User=" + id+ ";");
			
			while (rset.next()) {
 %>
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
 		<td>Parcours</td>
 		<td><%=rset.getString("parcours") %></td>
 	</tr>
 	<tr>
 		<td>Année d'études</td>
 		<td><%=rset.getString("studyyear") %></td>
 	</tr>
 	 <tr>
 		<td>TOEIC</td>
 		<td><%=rset.getInt("toeic") %></td>
 	</tr>
 	<tr>
 		<td></td>
 		<td></td>
 	</tr>
 </table>
 
 <%
			}
		}else{ //result of profil of teacher
			rset2 = statement2.executeQuery("select * from Users, Teachers where Users.id_User=" + id + " and Teachers.id_User=" + id+ ";");
			
			while (rset2.next()) {
 %>
 <p><%=id %></p>
 <table>
 	<tr>
 		<td>Nom</td>
 		<td><%=rset2.getString("lastName") %></td>
 	</tr>
 	<tr>
 		<td>Prénom</td>
 		<td><%=rset2.getString("firstName") %></td>
 	</tr>
 	<tr>
 		<td>Email</td>
 		<td><%=rset2.getString("email") %></td>
 	</tr>
 	<tr>
 		<td>Tél</td>
 		<td><%=rset2.getString("phone")%></td>
 	</tr>
 	<tr>
 		<td>Bureau</td>
 		<td><%=rset2.getString("office")%></td>
 	</tr>
 	<tr>
 		<td>Poste</td>
 		<td><%=rset2.getString("poste") %></td>
 	</tr>
 </table>
 
 <%
			}
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
		if ( rset2 != null ) {
			try {
				rset2.close();
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
		if ( statement2 != null ) {
			try {
				statement2.close();
				System.out.println("Fermeture du statement");
			} catch ( SQLException ignore ) {
			}
		}
	}

 %>
 <br><br> 
 
 <%
 User user = (User) request.getSession().getAttribute("user");
 if(user.getType().equals("student")) {
 %>
 	<input type="button" value="Return" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/recherchePourEleve.jsp'" />
<%
 }else{
 %>
	<input type="button" value="Return" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/recherchePourProf.jsp'" />
<%	 
 }

%>

</body>
</html>