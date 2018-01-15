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
<script type="text/javascript" src="DemandeDeContact.js"></script>
<script type="text/javascript" src="inscriptionCheck.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ISEP Conseil - Prendre rendez-vous</title>
</head>
<body>
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
			<div class="cycle">
			<div class="white"></div>
			<div class="white"></div>
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
				   resultat1 = statement1.executeQuery("select * from Camelia.Users where id_User in (select id_User from Camelia.Teachers);");
				  
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
				<br> <label for="but" id="But" >But:</label>
				<textarea id="but" name = "but"  onkeyup="wordStatic(this);" maxlength="120" required style="resize: none;vertical-align:top;"></textarea>
				<br><div class="textarea_counter">(<span id="num">0</span>/120)</div>  
				<br><br>
				<input id="submit" type="submit" value="Demander un RDV"
					class="button" onclick="" />
          </div>
			</center>
		</form>

	</div>


<div style="bottom:0px;font-size:0px;">
<img  src="pictures/blue1.png" style="height:200px;width:1200px"/>
</div>

</body>
</html>