<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.isepconseil.vo.Professeur" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../profilPourEleve.css">
<title>Insert title here</title>
</head>
<body>
<img src="../pictures/try.jpg" border='0'width='100%' height='100%' style='position: absolute;left:0px;top:0px;z-index: -1'/>
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


<h3>Nom = <%= nom%></h3>
<h3>Prénom = <%= prenom%></h3>
<h3>Email = <%= email%></h3>
<h3>Tél = <%= tel%></h3>
<h3>Bureau = <%=bureau %></h3>
<h3>Poste = <%= poste%></h3>



<input type="button" value="Modifier" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/Vue/ProfModifier.jsp'"  />
</center>
</body>
</html>