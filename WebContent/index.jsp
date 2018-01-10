<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>  
<%@page import="java.util.List"%> 
<%@ page import="fr.isepconseil.vo.User" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="indexStyle.css">
<title>ISEP Conseil - Login</title>
</head>
<body>
<img src="pictures/login3.jpg" border='0'width='100%' height='100%' style='position: absolute;left:0px;top:0px;z-index: -1'/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="indexScript.js" type="text/javascript"></script>


<div class= "center">
<p class="title">Bienvenue<p>
<%  
// Remove all the existant attributes in session => useful when you click déconnexion or rewrite address index.jsp

if(request.getSession().getAttribute("user")!=null){
	request.getSession().removeAttribute("user");
}
if(request.getSession().getAttribute("etudiant")!=null){
	request.getSession().removeAttribute("etudiant");
}
if(request.getSession().getAttribute("professeur")!=null){
	request.getSession().removeAttribute("professeur");
}


String info= (String) request.getAttribute("info");  
if(info!=null){  
     
%>  
<h4 style='color:red'><%=info%></h4>  
<%    
}  
%> 
	<form name="loginForm" method= "POST" action="loginServlet"> 
		<fieldset>
			<label for="login">Nom d'utilisateur:</label>
			<input id="login" type="text" name="login" />
			<br><br>
			<label for="password">Mot de passe:</label>
		    <input id="password" type="password" name="password" />
			<br><br>
	    </fieldset>
	    
	    <input id="submit" type="submit" value="Connexion" class = "button"/>
	  <input type="button" value="Inscription" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/Inscription.html'" />
	    <br>
	</form>
	<h3><a href="http://localhost:8080/ProjetCamelia/forget.jsp">Mot de passe oublié</a></h3>
</div>



<div id="message"></div>
<br>
</body>
</html>