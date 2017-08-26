package extractor;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import models.Entry;
import unsw.curation.api.domain.abstraction.IKeywordEx;
import unsw.curation.api.extractnamedentity.ExtractEntitySentence;
import unsw.curation.api.tokenization.ExtractionKeywordImpl;

public class Extractor {

	private static ExtractEntitySentence entityExtractor = new ExtractEntitySentence();
	private static IKeywordEx keywordExtractor = new ExtractionKeywordImpl();
	
	public static void main(String[] args) {
		try {
			System.out.println(entityExtractor.ExtractPerson("Oregon State Lottery Commission Mary Kay Wheat, Portland"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void highlightPeople (Entry entry) throws URISyntaxException{
		List<String> people = entityExtractor.ExtractPerson(entry.getContent());
		for (String name : people){
			entry.setContent(entry.getContent().replace(name.trim(), "<strong class=\"text-danger\">" + name.trim() + "</strong>"));
		}
	}
	
	public static List<String> extractPeople (Entry entry) throws URISyntaxException{
		return entityExtractor.ExtractPerson(entry.getContent());
	}
	
	public static void highlightOrganisations (Entry entry) throws URISyntaxException{
		List<String> orgs = entityExtractor.ExtractOrganization(entry.getContent());
		for (String org : orgs){
			entry.setContent(entry.getContent().replace(org.trim(), "<strong class=\"text-danger\">" + org.trim() + "</strong>"));
		}
	}
	
	public static List<String> extractOrganisations (Entry entry) throws URISyntaxException{
		return entityExtractor.ExtractOrganization(entry.getContent());
	}
	
	public static void highlightLocations (Entry entry) throws URISyntaxException{
		List<String> locations = entityExtractor.ExtractLocation(entry.getContent());
		for (String l : locations){
			entry.setContent(entry.getContent().replace(l.trim(), "<strong class=\"text-danger\">" + l.trim() + "</strong>"));
		}
	}
	
	public static List<String> extractLocations (Entry entry) throws URISyntaxException{
		return entityExtractor.ExtractLocation(entry.getContent());
	}
	
	public static void highlightKeywords (Entry entry) throws Exception{
		String keywords = keywordExtractor.ExtractSentenceKeyword(entry.getContent(), new File("WebContent/keywords/englishStopwords.txt"));
		String[] keywordsArray = keywords.split(",");
		for (String k : keywordsArray){
			entry.setContent(entry.getContent().replace(k.trim(), "<strong class=\"text-danger\">" + k.trim() + "</strong>"));
		}
	}
	
	public static String[] extractKeywords (Entry entry) throws Exception{
		return keywordExtractor.ExtractSentenceKeyword(entry.getContent(), new File("WebContent/keywords/englishStopwords.txt")).split(",");
	}
	
	public static String highlightMatchedEntities(String sentence, String searchString) {
		if (!searchString.isEmpty()) {
			return sentence.replace(searchString, "<strong class=\"text-danger\">" + searchString + "</strong>");
		}
		return sentence;
	}
	
}
