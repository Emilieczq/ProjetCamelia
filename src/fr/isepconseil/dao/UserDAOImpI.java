package fr.isepconseil.dao;

import fr.isepconseil.vo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAOImpI implements IUserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public UserDAOImpI(Connection conn) {
		this.conn = conn;
	}

	@Override
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
