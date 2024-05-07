package mvc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve cart items from session
		List<ProductModel> cartItems = (List<ProductModel>) request.getSession(false).getAttribute("cartItems");
		double orderTotal = 0.0;
		if (cartItems != null) {
			for (ProductModel item : cartItems) {
				orderTotal += item.getProd_price();
			}
		}

		try {
			OrderDAL orderdal = new OrderDAL();
			OrderModel orderModel = new OrderModel();
			orderdal.insertOrderAndItems(cartItems, orderTotal, orderModel);
			// Set the orderModel in request attribute
			request.setAttribute("orderModel", orderModel);

			// Forward the request to checkout.jsp
			request.getRequestDispatcher("/checkout.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle database errors
			// You may want to forward to an error page or display a message to the user
		}
	}
}