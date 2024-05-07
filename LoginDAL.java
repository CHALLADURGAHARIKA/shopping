package mvc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class LoginDAL implements ShoppingDAO {
	private final Properties properties = new Properties();

	public LoginDAL() {
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
			if (input == null) {
				System.err.println("Unable to find db.properties file");
				return;
			}
			properties.load(input);
			System.out.println("Loaded properties: " + properties); // Print loaded properties
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String validateUser(String username, String password) {
		String isValid = null;
		try {
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection(properties.getProperty("db.url"),
					properties.getProperty("db.user"), properties.getProperty("db.password"))) {
				String query = "SELECT password FROM cust_det_05 WHERE name=?";
				try (PreparedStatement st = conn.prepareStatement(query)) {
					st.setString(1, username);
					try (ResultSet rs = st.executeQuery()) {
						if (rs.next()) {
							String storedPassword = rs.getString("password");
							if (password.equals(storedPassword)) {
								isValid = "success";
							}
						}
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return isValid;
	}

	@Override
	public List<ProductModel> getAllProducts() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductModel> getProductsByCategoryId(int categoryId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertOrderAndItems(List<ProductModel> cartItems, double orderTotal, OrderModel orderModel)
			throws SQLException {
		// TODO Auto-generated method stub

	}
}