package mvc;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingDAO {
	// Product operations
	List<ProductModel> getAllProducts() throws SQLException;

	List<ProductModel> getProductsByCategoryId(int categoryId) throws SQLException;

	// Order operations
	void insertOrderAndItems(List<ProductModel> cartItems, double orderTotal, OrderModel orderModel)
			throws SQLException;

	// User operations
	String validateUser(String username, String password) throws SQLException;

}