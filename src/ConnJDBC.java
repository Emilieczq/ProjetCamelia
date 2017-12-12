import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnJDBC {

	private static String DBURL = "jdbc:oracle:thin:@localhost:1521/XE";
	private static String DBLOGIN = "root";
	private static String DBPASSWORD = "root";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Step1: register JDBC driver
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
          }
            catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
             System.exit(1);
      }
		// Step 2: Establish the connection to the database
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			conn = DriverManager.getConnection(DBURL, DBLOGIN, DBPASSWORD);
			// Step 3: Create statements (SQL queries)
			stmt = conn.createStatement();
			// Step 4: Execute statements and retrieve results
			rset = stmt.executeQuery("SELECT * FROM STUDENTS");
			// Step 5: Analyze results
			while (rset.next()) {
				System.out.println(rset.getString("testAttribute"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Step 6: Release resources
			try {
				if (rset != null)
					rset.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
