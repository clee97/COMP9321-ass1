package extractor;

import java.net.URISyntaxException;
import java.util.List;

import models.Entry;
import unsw.curation.api.extractnamedentity.ExtractEntitySentence;

public class Extractor {

	private static ExtractEntitySentence extractor = new ExtractEntitySentence();
	
	public static void main(String[] args) {
		try {
			System.out.println(extractor.ExtractOrganization("Today, Secretary of State Dennis Richardson released the annual report on the activities of the Government Waste Hotline."));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void extractPerson (Entry entry) throws URISyntaxException{
		List<String> people = extractor.ExtractPerson(entry.getContent());
		for (String name : people){
			entry.getContent().replace(name, "<strong class=\"text-danger\">" + name + "</strong>");
		}
	}
	
	public static void extractOrganisation (Entry entry) throws URISyntaxException{
		List<String> orgs = extractor.ExtractOrganization(entry.getContent());
		for (String org : orgs){
			entry.getContent().replace(org, "<strong class=\"text-danger\">" + org + "</strong>");
		}
		System.out.println(entry.getContent());
	}
	
	public static void extractLocation (Entry entry) throws URISyntaxException{
		List<String> locations = extractor.ExtractLocation(entry.getContent());
		for (String l : locations){
			entry.getContent().replace(l, "<strong class=\"text-danger\">" + l + "</strong>");
		}
	}
}
