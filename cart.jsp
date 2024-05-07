<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="mvc.ProductModel" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <style>
    body{
background-image: linear-gradient(to right, #ffbb33, #ff6f91);
    
    }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            
        }

        .cart-item {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 10px;
            margin-bottom: 20px;
            display: flex;
            align-items: center;
        }

        .cart-item img {
            width: 100px;
            height: auto;
            margin-right: 20px;
        }

        .cart-item-details {
            flex: 1;
        }

        .cart-item-details h3 {
            margin: 0;
        }

        .cart-item-details p {
            margin: 5px 0;
        }

        .remove-button {
            background-color: #ff6347;
            border: none;
            color: white;
            padding: 8px 16px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            cursor: pointer;
            border-radius: 4px;
        }

        .remove-button:hover {
            background-color: #ff4332;
        }

        .checkout-button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 8px 16px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            cursor: pointer;
            border-radius: 4px;
        }

        .checkout-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Shopping Cart</h2>
        <% 
        List<ProductModel> cartItems = (List<ProductModel>) request.getSession().getAttribute("cartItems");
        if (cartItems != null) {
            for (ProductModel item : cartItems) {
        %>
        <div class="cart-item">
            <div class="cart-item-details">
                <h3><%= item.getProd_name() %></h3>
                <p>Price: $<%= item.getProd_price() %></p>
                <button class="remove-button" onclick="removeItem('<%= item.getProd_ID() %>')">Remove</button>
            </div>
        </div>
        <% 
            }
        }
        %>
        <a href="ProductController" class="checkout-button">Continue Shopping</a>
        <a href="#" class="checkout-button" onclick="checkout()">Checkout</a>
    </div>

    <script>
        function checkout() {
            // Check if the user is logged in (you can use a session attribute to store login status)
            window.location.href = "login.html"; 
        }

        function removeItem(productId) {
            fetch('CartController?productId=' + productId, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    console.error('Failed to remove item from cart');
                }
            })
            .catch(error => {
                console.error('Error removing item from cart:', error);
            });
        }
    </script>
</body>
</html>