package mvc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	// protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	// IOException {
	// response.setContentType("text/html;charset=UTF-8");
	//
	// ProductDAO productDAO = new ProductDAO();
	// try {
	// List<ProductModel> products = productDAO.getAllProducts();
	// System.out.println("Number of products retrieved from database: " + products.size());
	// request.setAttribute("products", products);
	// request.getRequestDispatcher("/index.jsp").forward(request, response);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	// }
	// }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String categoryId = request.getParameter("category");
		String sort_by = request.getParameter("sort");
		System.out.println(sort_by);
		System.out.println(categoryId);

		ProductDAO productDAO = new ProductDAO();
		try {
			List<ProductModel> products = new ArrayList<>();
			if (categoryId != null && !categoryId.isEmpty()) {
				int categoryIdInt = Integer.parseInt(categoryId);
				products = productDAO.getProductsByCategoryId(categoryIdInt);
			} else if (categoryId == null) {
				// If no category is selected, fetch all products
				products = productDAO.getAllProducts();
			}
			// Sort the products based on the selected sorting order
			if (sort_by != null && !sort_by.isEmpty()) {
				if (sort_by.equals("asc")) {
					Collections.sort(products, Comparator.comparingDouble(ProductModel::getProd_price));
				} else if (sort_by.equals("desc")) {
					Collections.sort(products, Comparator.comparingDouble(ProductModel::getProd_price).reversed());
				}
			}
			request.setAttribute("products", products);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"An error occurred while fetching products.");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

}