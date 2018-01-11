<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
     <%@ page import="fr.isepconseil.vo.Professeur" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="eleveModifier.css">
<script src="eleveMotifier.js" type="text/javascript"></script>
<title>Le profil de prof</title>
</head>
<body>
<%@ include file = "MenuPourProf.jsp" %>
<div class = "white">
</div>
<div class = "white">
</div>
<br>
<center>
<form>
<img id = "preview" src="pictures/people.png"width="100"height="103" name = "pic" alt="">
<br>
<input  id = "f" type="file" accept="image/*" onchange="change()"/>
</form>
</center>


<%

	Professeur professeur = (Professeur)request.getSession().getAttribute("professeur");
	String pprenom = professeur.getPrenom();
	String pnom = professeur.getNom();
	String pemail = professeur.getEmail();
	String pposte = professeur.getPoste();
	String pbureau = professeur.getBureau();
	String ptel = professeur.getTel();
	


%>

<form name="ProfModifier" method="post" action="ProfModifierServlet">
<div class = "droit">
<label for="poste">Poste :</label>
<input id="poste" type="text" name="poste" value=<%=pposte%> class="normal"/>
<br><br>
<label for="bureau">Bureau :</label>
<input id="bureau" type="text" name="bureau" value=<%=pbureau%> class="normal"/>
<br><br>
<label for="tel">Tél :</label>
<input id="tel" type="text" name="tel" value=<%=ptel%> class="normal"/>
<br><br>
<label>Email :</label> 
<input id ="email" type="text" name ="email" value= <%= pemail %> class="normal_nochange" disabled/><br><br>

<br><br>
<input id="submit" type="submit" value="Enregistrer" class = "button" onclick="validate();"/>
<input type="button" value="Retour" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/ProfilPourProf.jsp'"  />
</div>
</form>
<div style="bottom:0px;font-size:0px;">
<img  src="pictures/double1.png" style="height:200px;width:1600px"/>
</div>

</body>
</html>