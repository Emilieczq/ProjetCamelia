<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="eleveMotifier.css">
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
<form>
<div class = "droit">
<label for="poste">Poste:</label>
<input id="poste" type="text" name="poste" class="normal"/>
<br><br>
<label for="ville">Ville:</label>
<input id="ville" type="text" name="ville" class="normal"/>
<br><br>
<label for="pays">Pays:</label>
<input id="pays" type="text" name="pays" class="normal"/>
<br><br>
<label for="bureau">Bureau:</label>
<input id="bureau" type="text" name="bureau" class="normal"/>
<br><br>
<label for="tel">Tel:</label>
<input id="tel" type="text" name="tel" class="normal"/>
<br><br>
<label for="email">Email:</label>
<input id="email" type="text" name="email" class="normal"/>
<br><br>
<input id="submit" type="submit" value="Enregistrer" class = "button" onclick="validate();"/>
<input type="button" value="Retour" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/ProfilPourProf.jsp'"  />
</div>
</form>

</body>
</html>