package data;

public class Review {
	private long score;
	private long time;
	private String summary;
	private String text;
	private User user;
	private Product product;
	private Helpfulness helpfulness;
	
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Helpfulness getHelpfulness() {
		return helpfulness;
	}
	public void setHelpfulness(Helpfulness helpfulness) {
		this.helpfulness = helpfulness;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
	return  "\nAvaliação: " + summary	;
	}
}
