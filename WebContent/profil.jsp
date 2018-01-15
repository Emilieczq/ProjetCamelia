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
<link rel="stylesheet" type="text/css" href="profil.css">
<title>ISEP Conseil - Profil</title>
</head>
<body>
<% User user = (User) request.getSession().getAttribute("user");
  if(user.getType().equals("student")){%>
  
	  <%@ include file = "menu.jsp" %>
	  <% } else { %>
	  
		  <%@ include file = "MenuPourProf.jsp" %>
		  <% }%>

<%  
	
	int id =  Integer.parseInt(request.getParameter("id"));  // get attribute by URL
	String type = request.getParameter("type"); // get attribute by URL
	DatabaseConnection dbc = null;
	Connection connexion = null;
	Statement statement = null, statement2=null,statement3=null,statement4=null,statement5=null;
	
	ResultSet rset = null, rset2=null,rset3=null,rset4=null,rset5=null;
	
	try{
		dbc = new DatabaseConnection();
		connexion = dbc.getConnection();
		statement = connexion.createStatement();
		statement2 = connexion.createStatement();
		statement3 = connexion.createStatement();
		statement4 = connexion.createStatement();
		statement5 = connexion.createStatement();
		
		if(type.equals("student")){
			rset = statement.executeQuery("select * from Users, Students where Users.id_User=" + id + " and Students.id_User=" + id+ ";");
			
			while (rset.next()) {
		
 %>

<br><div class = "white"></div><br><div class = "white"></div>

<center>
<p class="tips">Eleve</p>
<div class = "white"></div>
<div class="carreau">
 <table class="table-student">
    <tr>
 		<td></td>
 		<td></td>
 	</tr>
 	<tr>
 		<td>Nom :</td>
 		<td><%=rset.getString("lastName") %></td>
 	</tr>
 	<tr>
 		<td>Prénom :</td>
 		<td><%=rset.getString("firstName") %></td>
 	</tr>
 	<tr>
 		<td>Email :</td>
 		<td><%=rset.getString("email") %></td>
 	</tr>
 	<tr>
 		<td>Parcours :</td>
 		<td><%=rset.getString("parcours") %></td>
 	</tr>
 	<tr>
 		<td>Année d'études :</td>
 		<td><%=rset.getString("studyyear") %></td>
 	</tr>
 	 <tr>
 		<td>TOEIC :</td>
 		<td><%=rset.getInt("toeic") %></td>
 	</tr>
 	<tr>
 		<td></td>
 		<td></td>
 	</tr>
 </table>
 
 
 </div>

 <%
			}
			rset3 = statement3.executeQuery("select * from Users, Echange,Ecole where Users.id_User=" + id 
					+ " and Echange.id_User=" + id + " and Echange.id_Ecole = Ecole.id_Ecole;");
			while (rset3.next()) {
%>

<br><div class = "white"></div>
	<div class="carreau">
	<p class="tips">Echange d'A3</p>
	<div class = "white"></div>
	 <table class="table-student">
	    <tr>
	 		<td></td>
	 		<td></td>
	 	</tr>
	 	<tr>
	 		<td>Etablissement</td>
	 		<td><%=rset3.getString("Ename") %></td>
	 	</tr>
	 	<tr>
	 		<td>Ville :</td>
	 		<td><%=rset3.getString("eVille") %></td>
	 	</tr>
	 	<tr>
	 		<td>Pays :</td>
	 		<td><%=rset3.getString("ePays") %></td>
	 	</tr>
	 	<tr>
	 		<td>Début de l'échange :</td>
	 		<td><%=rset3.getString("eStart") %></td>
	 	</tr>
	 	<tr>
	 		<td>Fin de l'échange :</td>
	 		<td><%=rset3.getString("eEnd") %></td>
	 	</tr>
	 	<tr>
	 		<td></td>
	 		<td></td>
	 	</tr>
	 	
	 </table>
	</div>
 <%
			}	
		rset4 = statement4.executeQuery("select* from Users,Firm,Stages where Users.id_User="+id
				+" and Stages.id_User= "+id+" and Stages.id_Firm=Firm.id_Firm and sYear='A2';");
		while (rset4.next()) {
%>

			<br><div class = "white"></div><br>
			<div class="carreau">
			<p class="tips">Stage d'A2</p>
			<div class = "white"></div>
			 <table class="table-student">
			    <tr>
			 		<td></td>
			 		<td></td>
			 	</tr>
			 	<tr>
			 		<td>Entreprise :</td>
			 		<td><%=rset4.getString("fname") %></td>
			 	</tr>
			 	<tr>
			 		<td>Ville :</td>
			 		<td><%=rset4.getString("fville") %></td>
			 	</tr>
			 	<tr>
			 		<td>Pays :</td>
			 		<td><%=rset4.getString("fpays") %></td>
			 	</tr>
			 	<tr>
				 	<td>Début de l'échange :</td>
				 	<td><%=rset4.getString("sStart") %></td>
				 </tr>
				 <tr>
				 	<td>Fin de l'échange :</td>
				 	<td><%=rset4.getString("sEnd") %></td>
				 </tr>
			 	<tr>
			 		<td></td>
			 		<td></td>
			 	</tr>
			 </table>
			</div>
<%
			}	
		rset5 = statement5.executeQuery("select* from Users,Firm,Stages where Users.id_User="+id
				+" and Stages.id_User= "+id+" and Stages.id_Firm=Firm.id_Firm and sYear='A3';");
		while (rset5.next()) {
%>
		<br><div class = "white"></div><br>
			<div class="carreau">
			<p class="tips">Stage d'A3</p>
			<div class = "white"></div>
			 <table class="table-student">
			    <tr>
			 		<td></td>
			 		<td></td>
			 	</tr>
			 	<tr>
			 		<td>Entreprise :</td>
			 		<td><%=rset5.getString("fname") %></td>
			 	</tr>
			 	<tr>
			 		<td>Ville :</td>
			 		<td><%=rset5.getString("fville") %></td>
			 	</tr>
			 	<tr>
			 		<td>Pays :</td>
			 		<td><%=rset5.getString("fpays") %></td>
			 	</tr>
			 	<tr>
				 	<td>Début de l'échange :</td>
				 	<td><%=rset5.getString("sStart") %></td>
				 </tr>
				 <tr>
				 	<td>Fin de l'échange :</td>
				 	<td><%=rset5.getString("sEnd") %></td>
				 </tr>
			 	<tr>
			 		<td></td>
			 		<td></td>
			 	</tr>
			 </table>
			</div>

<%
		}
			
	}else{ //result of profil of teacher
		rset2 = statement2.executeQuery("select * from Users, Teachers where Users.id_User=" + id + " and Teachers.id_User=" + id+ ";");
			
		while (rset2.next()) {
 %>
 </center>
<br><div class = "white"></div><br>
<center>
<p class="tips">Professeur</p>
<div class = "white">
</div>
 <table class="table-teacher">
 	<tr>
 		<td></td>
 		<td></td>
 	</tr>
 	<tr>
 		<td>Nom :</td>
 		<td><%=rset2.getString("lastName") %></td>
 	</tr>
 	<tr>
 		<td>Prénom :</td>
 		<td><%=rset2.getString("firstName") %></td>
 	</tr>
 	<tr>
 		<td>Email :</td>
 		<td><%=rset2.getString("email") %></td>
 	</tr>
 	<tr>
 		<td>Tél :</td>
 		<td><%=rset2.getString("phone")%></td>
 	</tr>
 	<tr>
 		<td>Bureau :</td>
 		<td><%=rset2.getString("office")%></td>
 	</tr>
 	<tr>
 		<td>Poste :</td>
 		<td><%=rset2.getString("poste") %></td>
 	</tr>
 	<tr>
 		<td></td>
 		<td></td>
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
		if ( rset3 != null ) {
			try {
				rset3.close();
				System.out.println("Fermeture du resultat");
			} catch ( SQLException ignore ) {
			}
		}
		if ( rset4 != null ) {
			try {
				rset4.close();
				System.out.println("Fermeture du resultat");
			} catch ( SQLException ignore ) {
			}
		}		if ( rset != null ) {
			try {
				rset5.close();
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
		if ( statement3 != null ) {
			try {
				statement3.close();
				System.out.println("Fermeture du statement");
			} catch ( SQLException ignore ) {
			}
		}
		if ( statement4 != null ) {
			try {
				statement4.close();
				System.out.println("Fermeture du statement");
			} catch ( SQLException ignore ) {
			}
		}
		if ( statement5 != null ) {
			try {
				statement5.close();
				System.out.println("Fermeture du statement");
			} catch ( SQLException ignore ) {
			}
		}
	}

 %>
 <br><br> 
 
 <%
 if(user.getType().equals("student")) {
 %>
 	<input type="button" value="Return" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/recherchePourEleve.jsp'" />
<%
 }else{
 %>
	<input type="button" value="Return" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/recherchePourProf.jsp'" />
 </center>
<%	 
 }

%>
<div style="bottom:0px;font-size:0px;">
<img  src="pictures/blue1.png" style="height:200px;width:1200px"/>
</div>
</body>
</html>