<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"  type="text/css"  href="recherchePourEleve.css"/>
<title>Insert title here</title>
</head>
<body>
<%@ include file = "menu.jsp" %>
<br>
<div class = "white">
</div>
<div class = "white">
</div>
<br>


<center>
<div class ="test">

<div class = "ligne"></div>

<div>
<form name = "find" action="rechercheServlet">

<input type ="text" name = "search" class = "search" placeholder="Search">

</form>
<i class = "icon-user" onclick="find.submit()"></i>

</div>

<div class="ligne"></div>

</div>

</center>

</body>
</html>