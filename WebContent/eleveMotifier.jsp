<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="eleveMotifier.css">
<script src="eleveMotifier.js" type="text/javascript"></script>
<title>Le profil d'Eleve</title>
</head>
<body>
<%@ include file = "menu.jsp" %>
<br>
<div class = "white">
</div>
<center>

<form>
<img id = "preview" src="pictures/people.png"width="100"height="103" name = "pic" alt="">
<br>
<input  id = "f" type="file" accept="image/*" onchange="change()"/>

</center>
<div class = "droit">
		<label for="numero">Numéro d'étudiant:</label>
		<input id="numero" type="text" name="numero" class="normal"/>
		<br><br>
		<label for="annee">Année d’étude:</label>
		<input id="annee" type="text" name="annee" class="normal"/>
		<br><br>
		<label for="promotion">Promotion:</label>
		<input id="promotion" type="text" name="promotion" class="normal"/>
		<br><br>
		<label for="parcour">Parcours:</label>
		<input id="parcour" type="text" name="parcour" class="normal"/>
		<br><br>
		<label for="email">Email:</label>
		<input id="email" type="text" name="email" class="normal" />
		<br><br>
		<label for="toeic">Toeic:</label>
		<input id="toeic" type="text" name="toeic" class="normal" />
</div>
	<br><br><br>
	
	
<div class = "droit">

<label for="situation" class = "situation">Situation:</label>
<input type="radio" name="situation" value="echange" id="echange" class="situation" onclick="informationOfEchange.style.display='';informationOfStage.style.display='none';" checked>
<label for="echange">En échange</label>
<input type="radio" name="situation" value="stage"id="stage" class="situation" onclick="informationOfEchange.style.display='none';informationOfStage.style.display='';" checked>
<label for="stage">En stage</label>
<input type="radio" name="situation" value="AISEP"id="AISEP" class="situation" onclick="informationOfEchange.style.display='none';informationOfStage.style.display='none';" checked>
<label for="AISEP">A l'ISEP</label>

<div id ="informationOfEchange" style="display:none">
<br>
<label for="etablissement">Etablissment:</label>
<input id="etablissement" type="text" name="etablissement" class="normal"/>
<br><br>
<label for="ville">Ville:</label>
<input id="ville" type="text" name="ville" class="normal"/>
<br><br>
<label for="pays">Pays:</label>
<input id="pays" type="text" name="pays" class="normal"/>
<br><br>
</div>

<div id = "informationOfStage" style="display:none">
<br>
<label for="entreprises">Entreprises:</label>
<input id="entreprises" type="text" name="entreprises" class="normal"/>
<br><br>
<label for="poste">Poste:</label>
<input id="poste" type="text" name="poste" class="normal"/>
<br><br>
<label for="ville">Ville:</label>
<input id="ville" type="text" name="ville" class="normal"/>
<br><br>
</div>

</div>
<br>
<div class = "droit">
<label for="historieOfEchange" id = "historie">Histoire:</label>
<input type="radio" name="historie" value="historieOfEchange" id="historieOfEchange" class="historie" onclick="HEchange.style.display='';HStage.style.display='none';" checked>
<label for="historieOfEchange">Echange</label>
<input type="radio" name="historie" value="historieOfStage"id="historieOfStage" class="historie" onclick="HEchange.style.display='none';HStage.style.display='';" checked>
<label for="historieOfStage">Stage</label>
<br><br>
<div id = "HEchange" style="display:none">
<p class = "subTitle">Mon premiere échange</p>
<br><br>
<label for="annee">Année d’étude:</label>
		<select id = "annee" name = "annee" class="select">
			<option value="" disabled selected></option> 
  			<option valeur="A1">A1</option>
  			<option valeur="A2">A2</option>
  			<option valeur="A3">A3</option>
		</select>
		
		<br><br>
<label for="HEtablissement">Etablissment:</label>
<input id="HEtablissement" type="text" name="HEtablissement" class="normal"/>
<br><br>
<label for="HVille">Ville:</label>
<input id="HVille" type="text" name="HVille" class="normal"/>
<br><br>
<label for="HPays">Pays:</label>
<input id="HPays" type="text" name="HPays" class="normal"/>
<br><br>
<label for="HDuree">Durée:</label>
		<select id = "HDuree" name = "HDuree" class="select">
			<option value="" disabled selected></option> 
  			<option valeur="semestre">6 mois</option>
  			<option valeur="annee">une année</option>
		</select>
		<br><br>
<p class = "subTitle">Mon deuxième échange</p>
<br>
<label for="annee1">Année d’étude:</label>
		<select id = "annee1" name = "annee1" class="select">
			<option value="" disabled selected></option> 
  			<option valeur="A1">A1</option>
  			<option valeur="A2">A2</option>
  			<option valeur="A3">A3</option>
		</select>
		
		<br><br>
<label for="HEtablissement1">Etablissment:</label>
<input id="HEtablissement1" type="text" name="HEtablissement1" class="normal"/>
<br><br>
<label for="HVille1">Ville:</label>
<input id="HVille1" type="text" name="HVille1" class="normal"/>
<br><br>
<label for="HPays1">Pays:</label>
<input id="HPays1" type="text" name="HPays1" class="normal"/>
<br><br>
<label for="HDuree1">Durée:</label>
		<select id = "HDuree1" name = "HDuree1" class="select">
			<option value="" disabled selected></option> 
  			<option valeur="semestre">6 mois</option>
  			<option valeur="annee">une année</option>
		</select>
<br>

</div>


<div id = "HStage">
<p class = "subTitle">Mon premiere Stage</p>
<br><br>
<label for="annee2">Année d’étude:</label>
		<select id = "annee2" name = "annee2" class="select">
			<option value="" disabled selected></option> 
  			<option valeur="A1">A1</option>
  			<option valeur="A2">A2</option>
  			<option valeur="A3">A3</option>
		</select>
		
		<br><br>
<label for="HEntreprises">Entreprises:</label>
<input id="HEntreprises" type="text" name="HEntreprises" class="normal"/>
<br><br>
<label for="HPoste">Poste:</label>
<input id="HPoste" type="text" name="HPoste" class="normal"/>
<br><br>
<label for="Hville2">Ville:</label>
<input id="Hville2" type="text" name="Hville2" class="normal"/>
<br><br>
<label for="HDuree2">Durée:</label>
		<select id = "HDuree2" name = "HDuree2" class="select">
			<option value="" disabled selected></option>
			<option valeur="4mois">4 mois</option>
			<option valeur="5mois">5 mois</option> 
  			<option valeur="6mois">6 mois</option>
  			<option valeur="7mois">7 mois</option>
  			<option valeur="8mois">8 mois</option>
		</select>
				<br><br>
<label for="salaires">Salaire:</label>
<input id="salaires" type="text" name="salaires" class="normal"/>
<br><br>
<label for="adresse">Adresse:</label>
<input id="adresse" type="text" name="adresse" class="normal"/>
<br><br>
<label for="competences">Compétences:</label>
<input id="competences" type="text" name="competences" class="normal"/>
<br><br>
<label for="missions">Missions principales:</label>
<input id="missions" type="text" name="missions" class="normal"/>
<br><br>

<p class = "subTitle">Mon deuxième stage</p>
<br><br>
<label for="annee3">Année d’étude:</label>
		<select id = "annee3" name = "annee3" class="select">
			<option value="" disabled selected></option> 
  			<option valeur="A1">A1</option>
  			<option valeur="A2">A2</option>
  			<option valeur="A3">A3</option>
		</select>
		
		<br><br>
<label for="HEntreprises1">Entreprises:</label>
<input id="HEntreprises1" type="text" name="HEntreprises1" class="normal"/>
<br><br>
<label for="HPoste1">Poste:</label>
<input id="HPoste1" type="text" name="HPoste1" class="normal"/>
<br><br>
<label for="Hville3">Ville:</label>
<input id="Hville3" type="text" name="Hville3" class="normal"/>
<br><br>
<label for="HDuree3">Durée:</label>
		<select id = "HDuree3" name = "HDuree3" class="select">
			<option value="" disabled selected></option>
			<option valeur="4mois">4 mois</option>
			<option valeur="5mois">5 mois</option> 
  			<option valeur="6mois">6 mois</option>
  			<option valeur="7mois">7 mois</option>
  			<option valeur="8mois">8 mois</option>
		</select>
				<br><br>
<label for="salaires1">Salaire:</label>
<input id="salaires1" type="text" name="salaires1" class="normal"/>
<br><br>
<label for="adresse1">Adresse:</label>
<input id="adresse1" type="text" name="adresse1" class="normal"/>
<br><br>
<label for="competences1">Compétences:</label>
<input id="competences1" type="text" name="competences1" class="normal"/>
<br><br>
<label for="missions1">Missions principales:</label>
<input id="missions1" type="text" name="missions1" class="normal"/>
<br><br>

</div>




<input id="submit" type="submit" value="Enregistrer" class = "button" onclick="validate();"/>
</div>

	
</form>

</body>
</html>