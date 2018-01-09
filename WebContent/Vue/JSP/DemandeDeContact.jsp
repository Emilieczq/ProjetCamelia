<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="../DemandeDeContact.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<img src="../pictures/try.jpg" border='0'width='100%' height='100%' style='position: absolute;left:0px;top:0px;z-index: -1'/>
<%@ include file = "menu.jsp" %>
<div class = "white">
</div>
<div class = "white">
</div>
<br>

<div id = "RDV">
<center> <p class="title1">Vous voulez prendre un RDV :</p></center>
<div class = "white">
</div>
<br>
<form id="demande" action="">
<center>
<label for="professeur">Professeur:</label>
<select id = "annee" name = "annee" class="select"  required>
			<option value="" disabled selected></option> 
  			<option valeur="A">A</option>
  			<option valeur="B">B</option>
  			<option valeur="C">C</option>
</select>
<br><br>
<label for="but" id="But">But:</label>
<textarea id ="but"></textarea>
<br><br>
<br><br>
<input id="submit" type="submit" value="Demander un RDV" class = "button" onclick="validatePassword();"/>

</center>
</form>

</div>

</body>
</html>