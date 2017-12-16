<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="profilPourEleve.css">
<title>Insert title here</title>
</head>
<body>
<img src="pictures/try.jpg" border='0'width='100%' height='100%' style='position: absolute;left:0px;top:0px;z-index: -1'/>
<%@ include file = "MenuPourProf.jsp" %>
<div class = "white">
</div>
<div class = "white">
</div>
<br>
<center>

<jsp:useBean id="professeur" scope="application" class="fr.isepconseil.vo.Professeur"/>

<jsp:setProperty property="poste" name="professeur"/>
<jsp:setProperty property="bureau" name="professeur"/>
<jsp:setProperty property="tel" name="professeur"/>
<jsp:setProperty property="email" name="professeur"/>


<h3>Poste :<jsp:getProperty property="poste" name="professeur"/></h3>
<h3>Bureau :<jsp:getProperty property="bureau" name="professeur"/></h3>
<h3>Tel :<jsp:getProperty property="tel" name="professeur"/></h3>
<h3>Email :<jsp:getProperty property="email" name="professeur"/></h3>

<input type="button" value="Modifier" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/ProfModifier.jsp'"  />
</center>
</body>
</html>