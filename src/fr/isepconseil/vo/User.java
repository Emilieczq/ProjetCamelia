package fr.isepconseil.vo;

public class User {
	private String email ; // => email in Database
    private String password; // => password in Database
    public String getEmail() {  
        return email;  
    }  
    public void setUserid(String email) {  
        this.email = email;  
    }  
    public String getPassword() {  
        return password;  
    }  
    public void setPassword(String password) {  
        this.password = password;  
    }  
}
