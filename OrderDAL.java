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

public class OrderDAL implements ShoppingDAO {
	private final Properties properties = new Properties();

	public OrderDAL() {
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

	public void insertOrderAndItems(List<ProductModel> cartItems, double orderTotal, OrderModel orderModel)
			throws SQLException {

		String orderId = generateOrderId();
		String orderdate = generateOrderDate();
		OrderModel om = new OrderModel();
		try (Connection conn = DriverManager.getConnection(properties.getProperty("db.url"),
				properties.getProperty("db.user"), properties.getProperty("db.password"))) {
			conn.setAutoCommit(false);

			// Insert into orders_tab_05 table
			try (PreparedStatement orderStmt = conn
					.prepareStatement("INSERT INTO order_tab_05 (order_id,order_date,order_total) VALUES (?, ?,?)")) {
				orderStmt.setString(1, orderId);
				om.setOrderId(orderId);
				orderStmt.setString(2, orderdate);
				om.setOrderDate(orderdate);
				orderStmt.setDouble(3, orderTotal);
				om.setOrderTotal(orderTotal);
				orderStmt.executeUpdate();
			}

			// Insert into order_products_tab_05 table

			try (PreparedStatement itemStmt = conn
					.prepareStatement("INSERT INTO order_products_tab_05 (order_id,prod_id,price) VALUES (?, ?,?)")) {
				for (ProductModel item : cartItems) {
					itemStmt.setString(1, orderId);
					itemStmt.setInt(2, item.getProd_ID());

					itemStmt.setDouble(3, item.getProd_price());
					itemStmt.executeUpdate();
				}
			}
			// Populate the orderModel object with the order details
			orderModel.setOrderId(orderId);
			orderModel.setOrderDate(orderdate);
			orderModel.setOrderTotal(orderTotal);

			conn.commit();
		} catch (SQLException e) {
			// Handle exception
			e.printStackTrace();
		}
	}

	private String generateOrderId() throws SQLException {
		String orderId = null;
		try (Connection conn = DriverManager.getConnection(properties.getProperty("db.url"),
				properties.getProperty("db.user"), properties.getProperty("db.password"))) {
			try (PreparedStatement stmt = conn.prepareStatement("SELECT generate_order_id()")) {
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					orderId = rs.getString(1);
				}
			}
		}
		return orderId;
	}

	private String generateOrderDate() throws SQLException {
		String orderdate = null;
		try (Connection conn = DriverManager.getConnection(properties.getProperty("db.url"),
				properties.getProperty("db.user"), properties.getProperty("db.password"))) {
			try (PreparedStatement stmt = conn.prepareStatement("SELECT get_current_date()")) {
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					orderdate = rs.getString(1);
				}
			}
		}
		return orderdate;
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
	public String validateUser(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}