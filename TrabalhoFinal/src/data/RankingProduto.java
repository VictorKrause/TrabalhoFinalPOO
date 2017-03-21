package data;

public class RankingProduto implements Comparable<RankingProduto> {

	private double media;
	private Product product;

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getQtdReviews() {
		return media;
	}
	public void setMedia(double media) {
		this.media = media;
	}
	
	@Override
	public String toString() {
		return String.format(product + "\n" + "\n");
	}

	@Override
	public int compareTo(RankingProduto o) {
		if(this.media > o.getQtdReviews())
			return -1;
		else if (this.media < o.getQtdReviews())
			return 1;
		else 
			return 0;
	}
	
}
