<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page import="fr.isepconseil.vo.Etudiant" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="profilPourEleve.css">
<title>Insert title here</title>
</head>
<body>
<%@ include file = "menu.jsp" %>
<%

	Etudiant etudiant = (Etudiant)request.getSession().getAttribute("etudiant");
	String eemail = etudiant.getEmail();
	String eannee = etudiant.getAnnee();
	String eparcours = etudiant.getParcours();
	int ealternance1 = etudiant.getAlternance();
	System.out.println("Ici on a alternance = " + ealternance1);
	String eprenom = etudiant.getPrenom();
	String enom = etudiant.getNom();
	String ealternance ="";
	if (ealternance1 == 1){
		 ealternance = "Alternant" ;
	}
	else if(ealternance1 == 0){
		 ealternance = "Non-Alternant";
	}
	int etoeic1 = etudiant.getToeic();
	String etoeic = ""; 
	if (etoeic1 != 0){
		 etoeic = "Toeic :" + etoeic1;
	}
	


%>
<div class = "white">
</div>
<div class = "white">
</div>
<div class = "ligne"></div>
<div class = "ligne1"></div>
<div class = "white">
</div>
<div class = "white">
</div>
<div align = center>

<p><%=eprenom %> <%=enom %></p>
<br>
<p><%=eemail%></p><br>
<p>Année d'étude: <%=eannee %></p><br>
<p>Parcours: <%=eparcours%></p><br>
<p><%=ealternance%></p><br>
<p><%=etoeic%></p><br>

<br>
</div>
<br>
<center>



 <input type="button" value="Modifier" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/eleveModifier.jsp'"  />

</center>
</body>
</html>