package fr.isepconseil.vo;

public class Etudiant {

	
	private String parcours,annee,email,nom,prenom;
	private int idEtudiant, toeic, alternance;
	
	public int getIdEtudiant(){
		return idEtudiant;
	}
	
	public void setIdEtudiant(int idEtudiant){
		this.idEtudiant=idEtudiant;
	}
	
	public String getParcours() {
		return parcours;
	}
	public void setParcours(String parcours) {
		this.parcours = parcours;
	}
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	public int getAlternance() {
		return alternance;
	}
	public void setAlternance(int alternance) {
		this.alternance = alternance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getToeic() {
		return toeic;
	}
	public void setToeic(int toeic) {
		this.toeic = toeic;
	}
}
