<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="mvc.ProductModel" %>
<%@ page import="mvc.OrderModel" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <style>
        .card {
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 10px;
            margin: 10px;
            width: 300px;
            display: inline-block;
            vertical-align: top;
            background-color: #f2f2f2;
        }

        .card img {
            width: 100%;
            border-radius: 8px;
        }

        .card-content {
            padding: 10px;
        }

        .order-details {
            border: 2px solid #4CAF50;
            border-radius: 8px;
            padding: 10px;
            margin-top: 20px;
            background-color: #f2f2f2;
        }

        .order-details h2 {
            color: #4CAF50;
            font-size: 24px;
        }

        .order-details p {
            font-size: 16px;
            margin: 5px 0;
        }
body{
background-image: linear-gradient(to right, #ffbb33, #ff6f91);
    
    }
        .back-to-cart {
            display: inline-block;
            padding: 10px 20px;
            margin-top:20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }

        .back-to-cart:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Order Summary</h2>
    
    <div>
        <% 
            List<mvc.ProductModel> cartItems = (List<mvc.ProductModel>) request.getSession().getAttribute("cartItems");
            double orderTotal = 0.0;
            if (cartItems != null) {
                for (ProductModel item : cartItems) {
                    orderTotal += item.getProd_price();
        %>
        <div class="card">
            <div class="card-content">
                <h3><%= item.getProd_name() %></h3>
                <p>Product ID: <%= item.getProd_ID() %></p>
                <p>Price: <%= item.getProd_price() %></p>
            </div>
        </div>
        <% 
                }
            }
        %>
    </div>
    
   <div class="order-details">
    <h2>Order Details</h2>
    <% 
        OrderModel orderModel = (OrderModel) request.getAttribute("orderModel");
    %>
    <p>Order Total: <%= orderModel.getOrderTotal() %></p>
    <p>Order Date: <%= orderModel.getOrderDate() %></p>
    <p>Order ID: <%= orderModel.getOrderId() %></p>
</div>
    
    <a href="cart.jsp" class="back-to-cart">Back to Cart</a>
</body>
</html>