<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.isepconseil.vo.Professeur" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="profilPourEleve.css">
<title>Insert title here</title>
</head>
<body>

<%@ include file = "MenuPourProf.jsp" %>
<div class = "white">
</div>
<div class = "white">
</div>
<br>
<center>

<%-- <jsp:useBean id="professeur" scope="session" class="fr.isepconseil.vo.Professeur"/> --%>
<%-- <h3>Poste :<jsp:getProperty property="poste" name="professeur"/></h3> --%>
<%-- <h3>Bureau :<jsp:getProperty property="bureau" name="professeur"/></h3> --%>
<%-- <h3>Tel :<jsp:getProperty property="tel" name="professeur"/></h3> --%>
<%-- <h3>Email :<jsp:getProperty property="email" name="professeur"/></h3> --%>

<%

	Professeur professeur = (Professeur)request.getSession().getAttribute("professeur");
	String email = professeur.getEmail();
	String bureau = professeur.getBureau();
	String nom = professeur.getNom();
	String prenom = professeur.getPrenom();
	String tel = professeur.getTel();
	String poste = professeur.getPoste();

%>

<div class="cycle">
<br><br>
<p class="pen_style">Nom : <%= nom%></p><br>
<p class="pen_style">Prénom : <%= prenom%></p><br>
<p class="pen_style">Email : <%= email%></p><br>
<p class="pen_style">Tél : <%= tel%></p><br>
<p class="pen_style">Bureau : <%=bureau %></p><br>
<p class="pen_style">Poste : <%= poste%></p> 
</div>

<input type="button" value="Modifier" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/ProfModifier.jsp'"  />
</center>



</body>
</html>