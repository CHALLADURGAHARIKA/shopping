package mvc;

public class ProductModel {
	private int p_id;
	private String p_name;
	private double p_price;
	private String p_hsn;
	private String p_image;
	private int p_cate_id;

	// Constructor and other methods omitted for brevity

	// public ProductModel(int p_id, String p_name, double p_price, String p_hsn, String p_image, String p_category) {
	// this.p_id = p_id;
	// this.p_name = p_name;
	// this.p_price = p_price;
	// this.p_hsn = p_hsn;
	// this.p_image = p_image;
	// this.p_category = p_category;
	// }

	public void setProd_ID(int p_id) {
		this.p_id = p_id;
	}

	public void setProd_name(String p_name) {
		this.p_name = p_name;
	}

	public void setProd_price(double p_price) {
		this.p_price = p_price;
	}

	public void setProd_hsn(String p_hsn) {
		this.p_hsn = p_hsn;
	}

	public void setProd_img(String p_image) {
		this.p_image = p_image;
	}

	public void setProd_cate_ID(int p_cate_id) {
		this.p_cate_id = p_cate_id;
	}

	public int getProd_ID() {
		return p_id;
	}

	public String getProd_name() {
		return p_name;
	}

	public double getProd_price() {
		return p_price;
	}

	public String getProd_hsn() {
		return p_hsn;
	}

	public String getProd_img() {
		return p_image;
	}

	public int getProd_cate_ID() {
		return p_cate_id;
	}
}