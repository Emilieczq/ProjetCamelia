<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.isepconseil.vo.User" %>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import=" fr.isepconseil.dbc.DatabaseConnection"%>
<%@page import=" java.util.List"%>
<%@page import=" java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="ListeDesContactes.css">
<title>Insert title here</title>
</head>
<body>
<%@ include file = "MenuPourProf.jsp" %>
<div class = "white">
</div>
<div class = "white">
</div>
<br>
<center> <p class="title1">Vous avez les nouvelles demandes:</p></center>
<div class = "white">
</div>

<center>
<table id = "nouvelle" class="table-1">
<!-- Table header -->
<thead>
        <tr>
            <th scope="col" id="prenom">Prénom</th>
            <th scope="col" id="nom">Nom</th>
            <th scope="col" id="email">Email</th>
            <th scope="col" id="annee">Année d'étude</th>
            <th scope="col" id="parcours">Parcours</th>
            <th scope="col" id="but">But</th>
        </tr>
        
</thead>

<%
	User user = (User)request.getSession().getAttribute("user");
	System.out.println(user.getEmail());
	System.out.println(user.getId());
		
	int id_User = user.getId();
	List<String> idEtudiants = new ArrayList<String>();
	DatabaseConnection dbc = null;
	Connection connexion = null;
	Statement statement1 = null, statement2 = null,statement3 = null;
	
	ResultSet rset = null, rset2 = null, rset3 = null;
	dbc = new DatabaseConnection();
	connexion = dbc.getConnection();
	statement1 = connexion.createStatement();
	statement2 = connexion.createStatement();
	statement3 = connexion.createStatement();

	rset = statement1.executeQuery("select * from Camelia.RDV where id_Teacher = (select id_Teacher from Camelia.Teachers where id_User =" + id_User + ");");
	
	List<String> buts = new ArrayList<String>();
	
	while (rset.next()) {
		int id_Student = rset.getInt("id_Student");
		rset2 = statement2.executeQuery("select * from Students where id_Student=" + id_Student+";");
		rset3 = statement3.executeQuery("select * from Users where id_User = (select id_User from Students where id_Student = "+ id_Student+");");
		
		while (rset3.next()) {
%>
	<tr>
		<td><%= rset3.getString("firstName")%></td>
		<td><%= rset3.getString("lastName")%></td>
		<td><%= rset3.getString("email")%></td>
		
		<% 
			if(rset2.next()){
		%>
		<td><%= rset2.getString("studyyear")%></td>
		<td><%= rset2.getString("parcours")%></td>
		<td> <%= rset.getString("subject")%></td>
	</tr>

<% 		
			}
		}
	}

%>

</table>
</center>
<div class = "white">
</div>
<br>
<!-- <center> <p class="title1">Les demandes traitées:</p></center>
<div class = "white">
</div>
<center>

<table id = "traitees" class="table-1">
Table header
<thead>
        <tr>
            <th scope="col" id="prenom">Prénom</th>
            <th scope="col" id="nom">Nom</th>
            <th scope="col" id="email">Email</th>
            <th scope="col" id="annee">Année d'étude</th>
            <th scope="col" id="parcours">Parcours</th>
            <th scope="col" id="but">But</th>
        </tr>
</thead>

</table>
</center> -->

</body>
</html>