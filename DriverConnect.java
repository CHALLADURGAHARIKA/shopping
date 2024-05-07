package mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverConnect {
	public Connection connect_to_db(String db, String user, String pwd) {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/" + db, user, pwd);
			if (con != null) {
				System.out.println("Connection Established");
			} else {
				System.out.println("no connection");
			}
		} catch (SQLException | ClassNotFoundException ce) {
			ce.printStackTrace();
		}

		return con;
	}
}
