package mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CartController")
public class CartController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Extract product information from the request
		String productId = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String productPrice = request.getParameter("productPrice");

		// Create a ProductModel object
		ProductModel product = new ProductModel();
		product.setProd_ID(Integer.parseInt(productId));
		product.setProd_name(productName);
		product.setProd_price(Double.parseDouble(productPrice));

		// Retrieve the current cart items from session or create a new list
		List<ProductModel> cartItems = getCartItemsFromSession(request);
		// Add the new product to the cart
		cartItems.add(product);
		// Update the cart in session
		updateCartInSession(request, cartItems);

		// Redirect to index.jsp after adding to cart
		response.sendRedirect("index.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Display the cart contents
		List<ProductModel> cartItems = getCartItemsFromSession(request);
		request.getSession().setAttribute("cartItems", cartItems);
		// Forward the request to the cart.jsp page
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Extract product ID to be removed from the request
		String productIdToRemove = request.getParameter("productId");
		if (productIdToRemove != null) {
			int productId = Integer.parseInt(productIdToRemove);
			// Retrieve the current cart items from session
			List<ProductModel> cartItems = getCartItemsFromSession(request);
			// Remove the item with the specified ID from the cart
			cartItems.removeIf(item -> item.getProd_ID() == productId);
			// Update the cart in session
			updateCartInSession(request, cartItems);
			// Respond with success status
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			// If product ID is not provided in the request, respond with bad request status
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	private List<ProductModel> getCartItemsFromSession(HttpServletRequest request) {
		// Retrieve cart items from session or create a new list if not present
		List<ProductModel> cartItems = (List<ProductModel>) request.getSession().getAttribute("cart");
		if (cartItems == null) {
			cartItems = new ArrayList<>();
			request.getSession().setAttribute("cart", cartItems);
		}
		return cartItems;
	}

	private void updateCartInSession(HttpServletRequest request, List<ProductModel> cartItems) {
		// Update cart items in session
		request.getSession().setAttribute("cart", cartItems);
	}
}
