package fr.isepconseil.vo;

public class User {
	private String email; // => email in Database
	private String password; // => password in Database
	private String type; // => "Etudiant" or "Professeur"

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
