<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page import="fr.isepconseil.vo.Etudiant" %>
<%@page import=" java.util.List"%>
<%@page import=" java.util.ArrayList"%> 
<%@ page import="fr.isepconseil.vo.User" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resarchResult.css">
<title>ISEP Conseil - Resultat de votre recherche</title>
</head>
<body>
<% User user = (User) request.getSession().getAttribute("user");
  if(user.getType().equals("student")){%>
  
	  <%@ include file = "menu.jsp" %>
	  <% } else { %>
	  
		  <%@ include file = "MenuPourProf.jsp" %>
		  <% }%>
<br><br><br><br><br><br>

<img src="pictures/blue3.png" width="1300" height="100" />     
<center>

<%
List<String> results = (ArrayList<String>)request.getAttribute("results");
List<String> ids = (ArrayList<String>)request.getAttribute("ids");
List<String> types = (ArrayList<String>)request.getAttribute("types");
		
if(results.isEmpty()){
%>
	<p class="tips">Il n'y a aucun résultat associé.</p> <!-- need to modify css -->

<% 	
}
else{
%>
	<p class="tips">Il y a <%=results.size()%> résultats.</p>
	
	<br>
<% 
	for(int i=0; i<results.size();i++) {
		System.out.println(ids.get(i));
		%>
		
		<div class="result"><a class = "lien" href="http://localhost:8080/ProjetCamelia/profil.jsp?id=<%=ids.get(i)%>&type=<%=types.get(i)%>"><%=results.get(i) %> </a><br><br> </div> 
		
		<%	
	}
}
%>
<p> </p>

</center>
</body>
</html>