package data;

import java.util.Set;

public class Product {
	private String productId;
	private String title;
	private double price;
	private Set<Review> reviews;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Set<Review> getReviews() {
		return reviews;
	}
	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	@Override
	public String toString() {
		return "Id:" + productId + "\n" + "Título:" + title + "";
	}
	
}
