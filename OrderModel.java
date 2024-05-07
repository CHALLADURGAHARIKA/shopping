package mvc;

public class OrderModel {
	private String orderId;
	private String orderDate;
	private double orderTotal;

	// Constructor
	// public OrderModel(String orderId, String orderDate, double orderTotal) {
	// this.orderId = orderId;
	// this.orderDate = orderDate;
	// this.orderTotal = orderTotal;
	// }

	// Getters and setters
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}
}