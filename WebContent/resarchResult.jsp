<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page import="fr.isepconseil.vo.Etudiant" %>
<%@page import=" java.util.List"%>
<%@page import=" java.util.ArrayList"%> 
 
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
<%
List<String> results = (ArrayList<String>)request.getAttribute("results");
List<String> ids = (ArrayList<String>)request.getAttribute("ids");
		
if(results.isEmpty()){
%>
	<p>Il n'y a aucun résultat associé.</p>
<% 	
}
else{
	for(int i=0; i<results.size();i++) {
%>
		<p><%=i+1 %> : <a href="profil.jsp?id=<%=ids.get(i)%>"></a><%=results.get(i) %> </p> 
<%	
	}
}
%>
<p> </p>


</center>
</body>
</html>