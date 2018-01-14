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
		
	int id_User = user.getId();
	List<String> idEtudiants = new ArrayList<String>();
	DatabaseConnection dbc = null;
	Connection connexion = null;
	Statement statement1 = null, statement2 = null,statement3 = null,statement4=null,statement5=null;
	
	ResultSet rset = null, rset2 = null, rset3 = null,rset4=null,rset5=null;
	int ajout;
	
	
	
	try{
		dbc = new DatabaseConnection();
		connexion = dbc.getConnection();
		statement1 = connexion.createStatement();
		statement2 = connexion.createStatement();
		statement3 = connexion.createStatement();
		statement4 = connexion.createStatement();
		statement5 = connexion.createStatement();
		
		int id_RDV=0;
		List<String> buts = new ArrayList<String>();
		rset5 = statement5.executeQuery("select * from Camelia.RDV where id_Teacher = (select id_Teacher from Camelia.Teachers where id_User =" + id_User + ");");
		if(request.getParameter("id")!=null){
			id_RDV=Integer.parseInt(request.getParameter("id"));
			System.out.println(id_RDV);
			while(rset5.next()){
				
				ajout = statement4.executeUpdate( "UPDATE RDV SET status ='1' where id_RDV="+id_RDV+";");
			}
		}
		
		rset = statement1.executeQuery("select * from Camelia.RDV where id_Teacher = (select id_Teacher from Camelia.Teachers where id_User =" + id_User + ");");
				while (rset.next()) {
					
					if(rset.getInt("status")==0){
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
				<td><a href="http://localhost:8080/ProjetCamelia/ListeDesContactes.jsp?id=<%=rset.getInt("id_RDV")%>"> <div class="close">X</div></a></td>
				
				
			</tr>

		<%
							}
						}
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
				if ( statement1 != null ) {
					try {
						statement1.close();
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