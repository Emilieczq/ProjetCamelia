<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="indexStyle.css">
<title>ISEP Conseil - Forget your password</title>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="sendEmailScript.js" type="text/javascript"></script>
<img src="pictures/login3.jpg" border='0'width='100%' height='100%' style='position: absolute;left:0px;top:0px;z-index: -1'/>
	
	<div align="center">
	<h2 align="center">Envoyer un mail</h2>
	<form method="post" action="sendEmailServlet">
		Votre E-mail : <input id="email" type="text" name="email" />
		<br/><br/>
		<input type="submit" value="Envoyer" class = "button">
	
	</form>
		
	</div>
	
</body>
</html>