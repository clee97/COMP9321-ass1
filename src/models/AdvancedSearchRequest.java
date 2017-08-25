package models;

public class AdvancedSearchRequest {

	private String keyword;
	
	private String person;
	
	private String organisation;
	
	private String location;

	public AdvancedSearchRequest(String keyword, String person, String organisation, String location) {
		this.keyword = keyword;
		this.person = person;
		this.organisation = organisation;
		this.location = location;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
