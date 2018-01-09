<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../ListeDesContactes.css">
<title>Insert title here</title>
</head>
<body>
<img src="../pictures/try.jpg" border='0'width='100%' height='100%' style='position: absolute;left:0px;top:0px;z-index: -1'/>
<%@ include file = "MenuPourProf.jsp" %>
<div class = "white">
</div>
<div class = "white">
</div>
<br>
<center> <p class="title1">Vous avez les nouvelles demandes:</p></center>
<div class = "white">
</div>
<center>

<table id = "nouvelle" class="table-1">
<!-- Table header -->
<thead>
        <tr>
            <th scope="col" id="prenom">Prénom</th>
            <th scope="col" id="nom">Nom</th>
            <th scope="col" id="email">Email</th>
            <th scope="col" id="annee">Année d'étude</th>
            <th scope="col" id="parcours">Parcours</th>
            <th scope="col" id="but">But</th>
        </tr>
</thead>

</table>
</center>
<div class = "white">
</div>
<br>
<center> <p class="title1">Les demandes traitées:</p></center>
<div class = "white">
</div>
<center>

<table id = "traitees" class="table-1">
<!-- Table header -->
<thead>
        <tr>
            <th scope="col" id="prenom">Prénom</th>
            <th scope="col" id="nom">Nom</th>
            <th scope="col" id="email">Email</th>
            <th scope="col" id="annee">Année d'étude</th>
            <th scope="col" id="parcours">Parcours</th>
            <th scope="col" id="but">But</th>
        </tr>
</thead>

</table>
</center>

</body>
</html>