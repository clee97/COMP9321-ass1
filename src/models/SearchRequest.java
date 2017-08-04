package models;

import java.util.List;

public class SearchRequest {

	private String title;
	
	private String authors;
	
	private Integer year;
	
	private String volume;
	
	private String journal;
	

	public SearchRequest(String title, String authors, Integer year, String volume, String journal) {
		super();
		this.title = title;
		this.authors = authors;
		this.year = year;
		this.volume = volume;
		this.journal = journal;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}


}
