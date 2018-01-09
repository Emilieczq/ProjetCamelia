<%@page import="java.util.Iterator"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import=" fr.isepconseil.dbc.DatabaseConnection"%>
<%@page import=" java.util.List"%>
<%@page import=" java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="DemandeDeContact.css">
<script type="text/javascript" src="inscriptionCheck.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<img src="pictures/try.jpg" border='0' width='100%' height='100%'
		style='position: absolute; left: 0px; top: 0px; z-index: -1' />
	<%@ include file="menu.jsp"%>
	<div class="white"></div>
	<div class="white"></div>
	<br>

	<div id="RDV">
		<center>
			<p class="title1">Vous voulez prendre un RDV :</p>
		</center>
		<div class="white"></div>
		<br>
		<form id="demande"  method= "POST" action="EleveContactServlet">
			<center>
				<label for="professeur">Professeur:</label>
				<%
				   List<String> profs = new ArrayList<String>();
				   DatabaseConnection dbc = null;
				   Connection connexion = null;
				   Statement statement1 = null;

				   ResultSet resultat1;
				   dbc = new DatabaseConnection();
				   connexion = dbc.getConnection();
				   statement1 = connexion.createStatement();

						
				   resultat1 = statement1.executeQuery("select*from Camelia.Users where id_User in (select id_User from Camelia.Teachers);");
				  
				   int i =0;
				   while (resultat1.next()){
					   
					 	profs.add(resultat1.getString( "firstName" )+" " + resultat1.getString( "lastName" ));
					 	i++;
				   }
				   System.out.println(profs);
				%>
				<select id="profName" name="profName" class="select" required>
					<option value="" disabled selected></option>
					<%
					for(int j=0;j<i;j++){
					%>
					<option value="<%=profs.get(j)%>"><%=profs.get(j) %></option>
					
					<%
					}
					%>
					
				</select> <br>
				<br> <label for="but" id="But">But:</label>
				<textarea id="but" name = "but" required></textarea>
				<br><br> <br><br> 
				<input id="submit" type="submit" value="Demander un RDV"
					class="button" onclick="" />

			</center>
		</form>

	</div>

</body>
</html>