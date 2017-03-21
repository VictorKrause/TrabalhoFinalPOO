package data;

import java.util.Set;

public class User {
	private String id;
	private String profileName;
	private Set<Review> reviews;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public Set<Review> getReviews() {
		return reviews;
	}
	@Override
	public String toString() {
		return "Nome: " + profileName + "\n" + " Id: " + id;
	}
	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}	
}
