package fr.isepconseil.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.isepconseil.dbc.DatabaseConnection;
import fr.isepconseil.vo.Professeur;
import fr.isepconseil.vo.User;

public class UserDAOI {
	private DatabaseConnection dbc = null;
	private PreparedStatement pstmt = null;
	private Connection conn = null;
	private Professeur professeur = null;

	public UserDAOI() {
		try {
			dbc = new DatabaseConnection();
			conn = dbc.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean findLogin(User user) {
		boolean flag = false;
		try {
			String sql = "select email from Users where email=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPassword());
			ResultSet rSet = pstmt.executeQuery();
			if (rSet.next()) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	public String defineType(User user) {
		try {
			String sql = "select Students.id_Student from Students, Users where UserS.email=? and Users.id_User=Students.id_User ;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getEmail());
			ResultSet rSet = pstmt.executeQuery();
			
			if (rSet.next()) {
				user.setType("Etudiant");
				return "Etudiant";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "Professeur";
	}
	public void setProfesseur(User user, Professeur professeur) {
		String sql1 = "select * from Teachers where Id_User = (Select Id_User from Users where Email = ?);";
		
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, user.getEmail());
			ResultSet rSet = pstmt.executeQuery();
			
			if (rSet.next()) {
				String office = rSet.getString("office");
				String tel = rSet.getString("phone");
				professeur.setBureau(office);
				professeur.setTel(tel);
			}
			
			// name...
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		this.professeur = professeur;
	}
	
	public Professeur getProfesseur() {
		return professeur;
	}

}
