<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="fr.isepconseil.vo.Etudiant" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="eleveModifier.css">
<script src="eleveModifier.js" type="text/javascript"></script>
<title>Editer son profil</title>
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
<br><br>

</center>

<%

	Etudiant etudiant = (Etudiant)request.getSession().getAttribute("etudiant");
	String eemail = etudiant.getEmail();
	String eannee = etudiant.getAnnee();
	String eparcours = etudiant.getParcours();
	int ealternance1 = etudiant.getAlternance();
	String eprenom = etudiant.getPrenom();
	String enom = etudiant.getNom();
	if (ealternance1 == 0){
		String ealternance = "Alternant" ;
	}
	else{
		String ealternance = "Non-Alternant";
	}
	


%>

<form name="elevemodifier" method="post" action="EleveModifierServlet">
<div class = "droit">

<%=eprenom %> <%=enom %>
<br>
		<%= eemail%>
		<br><br>


		
		<label for="promotion">Année d’étude:  <%= eannee %></label>
		<select id = "annee" name = "promotion" class="select"  >
			<option value="" disabled selected></option> 
  			<option valeur="A1">A1</option>
  			<option valeur="A2">A2</option>
  			<option valeur="A3">A3</option>
		</select>
		<br><br>
		<label for="parcours">Parcours: 		<%= eparcours %></label>

<select id = "parcours" name = "parcours" class="selectlong"  >
			<option value=""></option> 
  			<option value = "architecte_des_systèmes_d information">ARCHITECTE DES SYSTÈMES D’INFORMATION</option>
  			<option value ="ingénieur_en_business_intelligence"> INGÉNIEUR EN BUSINESS INTELLIGENCE</option>
  			<option value ="ingénieur_logiciel"> INGÉNIEUR LOGICIEL</option>
  			<option value ="ingénieur_numerique_et_santé"> INGÉNIEUR NUMÉRIQUE ET SANTÉ</option>
  			<option value ="architecte_télècom&IOT"> ARCHITECTE TÉLÉCOM & IOT</option>
  			<option value ="ingénieur_en_sécurité_numérique_et_réseaux"> INGÉNIEUR EN SÉCURITÉ NUMÉRIQUE ET RÉSEAUX</option>
  			<option value ="concepteur_de_systèmes_embarqués"> CONCEPTEUR DE SYSTÈMES EMBARQUÉS</option>
  			<option value ="innovation_et_création d'entreprise"> INNOVATION ET CRÉATION D’ENTREPRISE</option>
		</select>		<br><br>

		<div id="alternancechoisir">  <label for="alternance">Alternance</label>
		
		<input type="radio" id="alternance" name="alternance" value = "alternance">
    	<input type="radio" id="non_alternance" name="alternance" value = "non_alternance">
    	<label for="non_alternance">Non Alternance</label>
    	</div>
    	<br>
		<label for="toeic">Toeic:</label>
		<input id="toeic" type="text" name="toeic" class="normal" />
</div>
	<br><br><br>
	
	
<div class = "droit">



</div>
<br>
<div class = "droit">

<br><br>
<div id = "HEchange" >
<p class = "subTitle">Mon premier échange</p>
<br><br>
<label for="annee">Année d’étude:</label>
		<select id = "annee" name = "annee" class="select">
			<option value="" disabled selected></option> 
  			<option valeur="A1">A1</option>
  			<option valeur="A2">A2</option>
  			<option valeur="A3">A3</option>
		</select>
		
		<br><br>
<label for="HEtablissement">Etablissement:</label>
<input id="HEtablissement" type="text" name="HEtablissement" class="normal"/>
<br><br>
<label for="HVille">Ville:</label>
<input id="HVille" type="text" name="HVille" class="normal"/>
<br><br>
<label for="HPays">Pays:</label>
<input id="HPays" type="text" name="HPays" class="normal"/>
<br><br>

<label for="debut1">Début de l'échange:</label>
<input id="debut1" type="date" name="debut1" class="normal"/>
<br><br>
<label for="fin1">Fin de l'échange:</label>
<input id="fin1" type="date" name="fin1" class="normal"/>
<br><br>
<br><br>

<p class = "subTitle">Mon deuxième échange</p>
<br><br>
<label for="annee1">Année d’étude:</label>
		<select id = "annee1" name = "annee1" class="select">
			<option value="" disabled selected></option> 
  			<option valeur="A1">A1</option>
  			<option valeur="A2">A2</option>
  			<option valeur="A3">A3</option>
		</select>
		
		<br><br>
<label for="HEtablissement1">Etablissement:</label>
<input id="HEtablissement1" type="text" name="HEtablissement1" class="normal"/>
<br><br>
<label for="HVille1">Ville:</label>
<input id="HVille1" type="text" name="HVille1" class="normal"/>
<br><br>
<label for="HPays1">Pays:</label>
<input id="HPays1" type="text" name="HPays1" class="normal"/>
<br><br>


<label for="debut2">Début de l'échange:</label>
<input id="debut2" type="date" name="debut2" class="normal"/>
<br><br>
<label for="fin2">Fin de l'échange:</label>
<input id="fin2" type="date" name="fin2" class="normal"/>
<br><br>
</div>

<br><br>
<div id = "HStage">
<p class = "subTitle">Mon premier Stage</p>
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
<label for="HPays2">Pays:</label>
<input id="HPays2" type="text" name="HPays2" class="normal"/>
<br><br>

<label for="salaires">Salaire:</label>
<input id="salaires" type="text" name="salaires" class="normal"/>
<br><br>

<label for="competences">Compétences:</label>
<input id="competences" type="text" name="competences" class="normal"/>
<br><br>
<label for="missions">Missions principales:</label>
<input id="missions" type="text" name="missions" class="normal"/>
<br><br>
<label for="debut3">Début du stage:</label>
<input id="debut3" type="date" name="debut3" class="normal"/>
<br><br>
<label for="fin3">Fin du stage:</label>
<input id="fin3" type="date" name="fin3" class="normal"/>
<br><br>

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
<label for="HPays3">Pays:</label>
<input id="HPays3" type="text" name="HPays3" class="normal"/>
<br><br>

<label for="salaires1">Salaire:</label>
<input id="salaires1" type="text" name="salaires1" class="normal"/>
<br><br>

<label for="competences1">Compétences:</label>
<input id="competences1" type="text" name="competences1" class="normal"/>
<br><br>
<label for="missions1">Missions principales:</label>
<input id="missions1" type="text" name="missions1" class="normal"/>
<br><br>
<label for="debut4">Début du stage:</label>
<input id="debut4" type="date" name="debut4" class="normal"/>
<br><br>
<label for="fin4">Fin du stage:</label>
<input id="fin4" type="date" name="fin4" class="normal"/>


</div>




<input id="submit" type="submit" value="Enregistrer" class = "button" onclick="validate();"/>
<input type="button" value="Retour" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/ProfilPourEleve.jsp'"  />
</div>

	
</form>

</body>
</html>