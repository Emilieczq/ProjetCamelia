<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.isepconseil.vo.Etudiant" %>
<%@ page import="fr.isepconseil.vo.User" %>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import=" fr.isepconseil.dbc.DatabaseConnection"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http ://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="eleveModifier.css">
<title>Editer son profil</title>
</head>
<body>
<script src="eleveModifier.js"></script>
<br><div class = "white"></div>
<center>
<form>
<img id = "preview" src="pictures/people.png"width="100"height="103" name = "pic" alt=""><br>
<input  id = "f" type="file" accept="image/*" onchange="change()"/>
</form>
<br><br>
</center>

<%

	Etudiant etudiant = (Etudiant)request.getSession().getAttribute("etudiant");
	String eemail = etudiant.getEmail();
	String eannee = etudiant.getAnnee();
	String eparcours = etudiant.getParcours();
	int ealternance = etudiant.getAlternance();
	String eprenom = etudiant.getPrenom();
	String enom = etudiant.getNom();
	
	
	
	
	// decide to show which studyyear as default if user has selected before
	//switch-case doesn't work because of the version of java
	String a0="",a1="",a2="",a3="";
	if(eannee.equals("A1")){
		a1="selected";
	}else if (eannee.equals("A2")){
		a2="selected";
	}else if (eannee.equals("A3")) {
		a3="selected";
	}else{
		a0="selected";
	}
	
	
	// decide to show which parcours as default if user has selected before
	String s0="",s1="",s2="",s3="",s4="",s5="",s6="",s7="",s8="";
	if(eparcours.equals("ARCHITECTE DES SYSTEMES D'INFORMATION")){
		s1="selected";
	}else if (eparcours.equals("INGENIEUR EN BUSINESS INTELLIGENCE")){
		s2="selected";
	}else if (eparcours.equals("INGENIEUR LOGICIEL")){
		s3="selected";
	}else if (eparcours.equals("INGENIEUR NUMERIQUE ET SANTE")){
		s4="selected";
	}else if (eparcours.equals("ARCHITECTE TELECOM & IOT")){
		s5="selected";
	}else if (eparcours.equals("INGENIEUR EN SECURITE NUMERIQUE ET RESEAUX")){
		s6="selected";
	}else if (eparcours.equals("CONCEPTEUR DE SYSTEMES EMBARQUES")){
		s7="selected";
	}else if (eparcours.equals("INNOVATION ET CREATION D'ENTREPRISE")){
		s8="selected";
	}else{
		s0="selected";
	}
	
	// decide to choose alternance or non-alternance if user has selected before
	String al0="",al1="";
	if(ealternance==0){
		al0="checked";
	}else if (ealternance==1){
		al1="checked";
	}
	
%>

<form id = "myForm" name="elevemodifier" method="post" action="EleveModifierServlet">
		<div class="droit">
			<label>Prénom :</label> <input type="text" value= <%= eprenom %> class="normal_nochange" disabled/><br><br>
			<label>Nom :</label> <input type="text" value= <%= enom %> class="normal_nochange" disabled/><br><br>
			<label>Email :</label> <input type="text" value= <%= eemail %> class="normal_nochange" disabled/><br><br>
			<label for="promotion">Année d’étude : </label>
			<select id="annee" name="promotion" class="select">
				<option value="" <%= a0 %>></option>
				<option value="A1" <%= a1 %>>A1</option>
				<option value="A2" <%= a2 %>>A2</option>
				<option value="A3" <%= a3 %>>A3</option>
			</select> <br><br> 
			<label for="parcours">Parcours :</label> <select
				id="parcours" name="parcours" class="selectlong">
				<option value="" <%= s0 %>></option>
				<option value="ARCHITECTE DES SYSTEMES D''INFORMATION" <%= s1 %>>ARCHITECTE DES SYSTÈMES D’INFORMATION</option>
				<option value="INGENIEUR EN BUSINESS INTELLIGENCE" <%= s2 %>> INGÉNIEUR EN BUSINESS INTELLIGENCE</option>
				<option value="INGENIEUR LOGICIEL" <%= s3 %>> INGÉNIEUR LOGICIEL</option>
				<option value="INGENIEUR NUMERIQUE ET SANTE" <%= s4 %>> INGÉNIEUR NUMÉRIQUE ET SANTÉ</option>
				<option value="ARCHITECTE TELECOM & IOT" <%= s5 %>> ARCHITECTE TÉLÉCOM & IOT</option>
				<option value="INGENIEUR EN SECURITE NUMERIQUE ET RESEAUX" <%= s6 %>> INGÉNIEUR EN SÉCURITÉ NUMÉRIQUE ET RÉSEAUX</option>
				<option value="CONCEPTEUR DE SYSTEMES EMBARQUES" <%= s7 %>> CONCEPTEUR DE SYSTÈMES EMBARQUÉS</option>
				<option value="INNOVATION ET CREATION D''ENTREPRISE" <%= s8 %>> INNOVATION ET CRÉATION D’ENTREPRISE</option>
			</select> <br><br>

			<div id="alternancechoisir">

				<input type="radio" id="alternance" name="alternance" value="1" checked =<%= al1 %>>
				<label for="alternance" >Alternance</label>
				<input type="radio" id="non_alternance" name="alternance" value="0" checked =<%= al0 %>> 
				<label for="non_alternance" >Non Alternance</label>
			</div>
			<br> <label for="toeic">Toeic :</label> <input id="toeic"
				type="text" name="toeic" class="normal" value= "<%=etudiant.getToeic() %>" />
		</div>
		<br><br><br>
<div class = "droit"></div>
<br>
<div class = "droit">

<br><br>
<div id = "HEchange" >
<p class = "subTitle">Mon premier échange</p><br><br>
<label>Année d’étude :</label>
		<select name = "annee1" class="select">
			<option value="" disabled selected></option> 
  			<option value="A1">A1</option>
  			<option value="A2">A2</option>
  			<option value="A3">A3</option>
		</select>
		<br><br>
<label>Etablissement :</label><input type="text" name="e1Etablissement" class="normal"/><br><br>
<label>Ville :</label><input type="text" name="e1Town" class="normal"/><br><br>
<label>Pays :</label><input type="text" name="eCountry" class="normal"/><br><br>
<label>Début de l'échange :</label><input type="date" name="e1Start" class="normal"/><br><br>
<label>Fin de l'échange :</label><input type="date" name="e1End" class="normal"/><br><br><br><br>


<p class = "subTitle">Mon deuxième échange</p><br><br>
<label>Année d’étude :</label>
		<select name = "annee2" class="select">
			<option value="" disabled selected></option> 
  			<option value="A1">A1</option>
  			<option value="A2">A2</option>
  			<option value="A3">A3</option>
		</select>
		<br><br>
<label>Etablissement :</label><input type="text" name="e1Etablissement" class="normal"/><br><br>
<label>Ville :</label><input type="text" name="e2Town" class="normal"/><br><br>
<label>Pays :</label><input type="text" name="e2Country" class="normal"/><br><br>
<label>Début de l'échange :</label><input type="date" name="e2Start" class="normal"/><br><br>
<label>Fin de l'échange :</label><input type="date" name="e2End" class="normal"/>
<br><br>
</div>


<br><br>
<%
//for the information of stages, need to connect to the database
		User user = (User)request.getSession().getAttribute("user");
		int id_User = user.getId();
		System.out.println("------id_User--------"+id_User); //à supprimer

		DatabaseConnection dbc = null;
		Connection connexion = null;
		Statement statement1 = null, statement2=null,statement3 = null, statement4=null;
		ResultSet rset1 = null, rset2=null,rset3 = null, rset4=null;
		
		String s1Firm="",s1Job="",s1Town="", s1Country="", competences1="", mission1="", s1Salary="", s1Start="", s1End="";
		String s2Firm="",s2Job="",s2Town="", s2Country="", competences2="", mission2="", s2Salary="", s2Start="", s2End="";
		int id_Firm1=0, id_Firm2=0;

		try{
			dbc = new DatabaseConnection();
			connexion = dbc.getConnection();
			statement1 = connexion.createStatement();
			statement2 = connexion.createStatement();
			statement3 = connexion.createStatement();
			statement4 = connexion.createStatement();
			
			rset1 = statement1.executeQuery("select * from Camelia.Stages where id_User =" + id_User + " && sYear= 'A2';");
			
			if(rset1.next()){
					
					s1Job = rset1.getString("sJob");
					s1Salary = rset1.getString("sSalary");
					s1Start = rset1.getString("sStart");
					s1End = rset1.getString("sEnd");
					id_Firm1 = rset1.getInt("id_Firm");
					competences1 = rset2.getString("competences1");
					mission1 = rset2.getString("mission1");
			}
			
			rset2 = statement2.executeQuery("select * from Camelia.Firms where id_Firm =" + id_Firm1 + ";");
			
			if(rset2.next()){
				s1Firm = rset2.getString("fName");
				s1Town = rset2.getString("ville");
				s1Country = rset2.getString("pays");
			}
			
			rset3 = statement3.executeQuery("select * from Camelia.Stages where id_User =" + id_User + " && sYear= 'A3';");
			
			if(rset3.next()){
					s2Job = rset3.getString("sJob");
					s2Salary = rset3.getString("sSalary");
					s2Start = rset3.getString("sStart");
					s2End = rset3.getString("sEnd");
					id_Firm2 = rset3.getInt("id_Firm");
					competences1 = rset3.getString("competences1");
					mission1 = rset3.getString("mission1");
			}
			
			rset4 = statement4.executeQuery("select * from Camelia.Firms where id_Firm =" + id_Firm2 + ";");
			
			if(rset4.next()){
				s2Firm = rset4.getString("fName");
				s2Town = rset4.getString("ville");
				s2Country = rset4.getString("pays");
			}
			
			
		}catch(Exception e) {
			System.out.println("Exception declenchee");
			e.printStackTrace();
		}
		finally {

			if ( rset1 != null ) {
				try {
					rset1.close();
					System.out.println("Fermeture du resultat");
				} catch ( SQLException ignore ) {
				}
			}
			if ( rset2 != null ) {
				try {
					rset1.close();
					System.out.println("Fermeture du resultat");
				} catch ( SQLException ignore ) {
				}
			}
			if ( rset3 != null ) {
				try {
					rset1.close();
					System.out.println("Fermeture du resultat");
				} catch ( SQLException ignore ) {
				}
			}
			if ( rset4 != null ) {
				try {
					rset1.close();
					System.out.println("Fermeture du resultat");
				} catch ( SQLException ignore ) {
				}
			}
			if ( statement1 != null ) {
				try {
					statement1.close();
					System.out.println("Fermeture du statement");
				} catch ( SQLException ignore ) {
				}
			}
			if ( statement2 != null ) {
				try {
					statement1.close();
					System.out.println("Fermeture du statement");
				} catch ( SQLException ignore ) {
				}
			}
			if ( statement3 != null ) {
				try {
					statement1.close();
					System.out.println("Fermeture du statement");
				} catch ( SQLException ignore ) {
				}
			}
			if ( statement4 != null ) {
				try {
					statement1.close();
					System.out.println("Fermeture du statement");
				} catch ( SQLException ignore ) {
				}
			}
		}
		
%>
<div id = "HStage">
	<p class = "subTitle">Mon premier Stage (A2)</p>
	<p> Merci d'entrer l'intégralité des données demandées</p><br><br>
	
	<label>Entreprise :</label><input id="s1Firm" type="text" name="s1Firm" class="normal" value="<%=s1Firm %>"/><br><br>
	<label>Poste :</label><input id="s1Job" type="text" name="s1Job" class="normal" value="<%=s1Job %>"/><br><br>
	<label>Ville :</label><input id="s1Town" type="text" name="s1Town" class="normal" value="<%=s1Town %>"/><br><br>
	<label>Pays :</label><input id = "s1Country" type="text" name="s1Country" class="normal" value="<%=s1Country %>"/><br><br>
	<label>Salaire :</label><input id = "s1Salary" type="text" name="s1Salary" class="normal" value="<%=s1Salary %>"/><br><br>
	<label>Compétences :</label><input id = "competences1" type="text" name="competences1" class="normal" value="<%=competences1 %>"/><br><br>
	<label>Missions principales :</label><input id="missions1" type="text" name="missions1" class="normal" value="<%=mission1 %>"/><br><br>
	<label>Début du stage :</label><input id = "s1Start" type="date" name="s1Start" class="normal" value="<%=s1Start %>"/><br><br>
	<label>Fin du stage :</label><input id = "s1End" type="date" name="s1End" class="normal" value="<%=s1End %>"/><br><br><br><br>
	
	<p class = "subTitle">Mon deuxième stage (A3)</p>
	<p> Merci d'entrer l'intégralité des données demandées</p><br><br>
	
	<label>Entreprise :</label><input id="s2Firm" type="text" name="s2Firm" class="normal" value="<%=s2Firm %>"/><br><br>
	<label>Poste :</label><input id="s2Job" type="text" name="s2Job" class="normal" value="<%=s2Job %>"/><br><br>
	<label>Ville :</label><input id="s2Town" type="text" name="s2Town" class="normal" value="<%=s2Town %>"/><br><br>
	<label>Pays :</label><input id = "s2Country" type="text" name="s2Country" class="normal" value="<%=s2Country %>"/><br><br>
	<label>Salaire :</label><input id = "s2Salary" type="text" name="s2Salary" class="normal" value="<%=s2Salary %>"/><br><br>
	<label>Compétences :</label><input id = "competences2" type="text" name="competences2" class="normal" value="<%=competences2 %>"/><br><br>
	<label>Missions principales :</label><input id="missions2" type="text" name="missions2" class="normal" value="<%=mission2 %>"/><br><br>
	<label>Début du stage :</label><input id = "s2Start" type="date" name="s2Start" class="normal" value="<%=s2Start %>"/><br><br>
	<label>Fin du stage :</label><input id = "s2End" type="date" name="s2End" class="normal" value="<%=s2End %>"/><br><br><br><br>
</div>



<input id="submit" type="submit" value="Enregistrer" class = "button" />
<input type="button" value="Retour" class = "button" onclick="location.href='http://localhost:8080/ProjetCamelia/ProfilPourEleve.jsp'"  />
</div>

	
</form>

</body>
</html>