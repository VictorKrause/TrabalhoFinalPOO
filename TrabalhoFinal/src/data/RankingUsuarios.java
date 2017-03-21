package data;

public class RankingUsuarios implements Comparable<RankingUsuarios>{

	private int reviewsUteis;
	private User user;
	
	public int getReviewsUteis() {
		return reviewsUteis;
	}
	public void setReviewsUteis(int reviewsUteis) {
		this.reviewsUteis = reviewsUteis;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return  user + "\n" + "\n";
	}
	
	@Override
	public int compareTo(RankingUsuarios o) {
		if(this.reviewsUteis > o.getReviewsUteis())
			return -1;
		else if (this.reviewsUteis < o.getReviewsUteis())
			return 1;
		else 
			return 0;
	}
}
