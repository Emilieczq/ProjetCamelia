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
<br>
<div class = "white">
</div>
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

<form action="/ProjetCamelia/ProfilPourProf.jsp">
<div class = "droit">
<label for="poste">Poste: <%=pposte%></label>
<input id="poste" type="text" name="poste" class="normal"/>
<br><br>
<label for="bureau">Bureau:  <%=pbureau%></label>
<input id="bureau" type="text" name="bureau" class="normal"/>
<br><br>
<label for="tel">Tel:  <%=ptel%></label>
<input id="tel" type="text" name="tel" class="normal"/>
<br><br>
<label for="email">Email:  <%=pemail%></label>
<input id="email" type="text" name="email" class="normal"/>
<br><br>
<input id="submit" type="submit" value="Enregistrer" class = "button"/>
<input type="button" value="Retour" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/ProfilPourProf.jsp'"  />
</div>
</form>

</body>
</html>