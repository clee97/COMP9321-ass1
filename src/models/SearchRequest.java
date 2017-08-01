package models;

public class SearchRequest {

	private String title;
	
	private Integer year;
	
	private String isbn;
	
	private String authors;
	
	private String venue;

	public SearchRequest(String title, Integer year, String isbn, String authors, String venue) {
		this.title = title;
		this.year = year;
		this.isbn = isbn;
		this.authors = authors;
		this.venue = venue;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	
}
