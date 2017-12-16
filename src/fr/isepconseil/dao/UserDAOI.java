package fr.isepconseil.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.isepconseil.dbc.DatabaseConnection;
import fr.isepconseil.vo.User;

public class UserDAOI {
	private DatabaseConnection dbc = null;
	private PreparedStatement pstmt = null;
	private Connection conn = null;

	public UserDAOI() {
		try {
			dbc = new DatabaseConnection();
			conn = dbc.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean findLogin(User user) throws Exception {
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
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return flag;
	}

}
