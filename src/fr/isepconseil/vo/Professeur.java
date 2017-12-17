package fr.isepconseil.vo;

import java.io.Serializable;

public class Professeur implements Serializable{
	private String poste,bureau,tel,email;
	
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	public String getBureau() {
		return bureau;
	}
	public void setBureau(String bureau) {
		this.bureau = bureau;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Professeur() {
		// TODO Auto-generated constructor stub
		
	}

}
