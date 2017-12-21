package fr.isepconseil.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.isepconseil.dbc.DatabaseConnection;
import fr.isepconseil.vo.Etudiant;
import fr.isepconseil.vo.Professeur;
import fr.isepconseil.vo.User;

public class UserDAOI {
	private DatabaseConnection dbc = null;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt2 = null;
	private Connection conn = null;
	private Professeur professeur = null;
	private Etudiant etudiant = null;

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
		String sql2 = "select * from Users where Email = ?;";
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, user.getEmail());
			ResultSet rSet = pstmt.executeQuery();
			
			if (rSet.next()) {
				professeur.setBureau(rSet.getString("office"));
				professeur.setTel( rSet.getString("phone"));
				user.setId(rSet.getInt("id_User"));
				professeur.setEmail(user.getEmail());
				professeur.setPoste(rSet.getString("poste"));
				
			}
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, user.getEmail());
			ResultSet rSet2 = pstmt2.executeQuery();
			
			if(rSet2.next()) {
				professeur.setPrenom(rSet2.getString("firstName"));
				professeur.setNom(rSet2.getString("lastName"));
			}
			
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
	
	public void setEtudiant(User user, Etudiant etudiant) {
		String sql1 = "select * from Students where Id_User = (Select Id_User from Users where Email = ?);";
		String sql2 = "select * from Users where Email = ?;";
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, user.getEmail());
			ResultSet rSet = pstmt.executeQuery();
			
			if (rSet.next()) {
				etudiant.setParcours(rSet.getString("parcours"));
				etudiant.setAnnee( rSet.getString("studyyear"));
				user.setId(rSet.getInt("id_User"));
				etudiant.setEmail(user.getEmail());
				etudiant.setAlternance(rSet.getInt("alternance"));
				etudiant.setToeic(rSet.getInt("toeic"));
				
			}
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, user.getEmail());
			ResultSet rSet2 = pstmt2.executeQuery();
			
			if(rSet2.next()) {
				etudiant.setPrenom(rSet2.getString("firstName"));
				etudiant.setNom(rSet2.getString("lastName"));
			}
			
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
		this.etudiant = etudiant;
	}
	public Professeur getProfesseur() {
		return professeur;
	}
	public Etudiant getEtudiant(){
		return etudiant;
	}

}
