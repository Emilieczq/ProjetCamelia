<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    <%@ page import="fr.isepconseil.vo.Etudiant" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="profilPourEleve.css">
<title>Insert title here</title>
</head>
<body>
<img src="pictures/try.jpg" border='0'width='100%' height='100%' style='position: absolute;left:0px;top:0px;z-index: -1'/>
<%@ include file = "menu.jsp" %>
 <br>
  <br>
   <br>
    <br>
     <br>
      <br>
      

<center>

 <p> Le prénom de la personne que vous rechercher :  <%  String fname = (String) request.getAttribute("fnameRech");
 out.println( fname ); %>
</p>
<p> Le nom de la personne que vous rechercher : 

 <%  String lname = (String) request.getAttribute("lnameRech");
 out.println( lname ); %>
 </p>
 
 <p> L'id de la personne que vous rechercher : 
 
  <%  String id = (String) request.getAttribute("idRech");
 out.println( id ); %>
 </p>

 <input type="button" value="Recherche" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/recherchePourEleve.jsp'"  />

</center>
</body>
</html>