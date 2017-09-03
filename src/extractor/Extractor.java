package extractor;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.Entry;
import unsw.curation.api.domain.abstraction.IKeywordEx;
import unsw.curation.api.extractnamedentity.ExtractEntitySentence;
import unsw.curation.api.tokenization.ExtractionKeywordImpl;

public class Extractor {

	private static ExtractEntitySentence entityExtractor = new ExtractEntitySentence();
	private static IKeywordEx keywordExtractor = new ExtractionKeywordImpl();
	
	public static void highlightPeople (Entry entry) throws URISyntaxException{
		List<String> people = entityExtractor.ExtractPerson(entry.getContent());
		for (String name : people){
			entry.setContent(entry.getContent().replace(name.trim(), "<strong class=\"text-danger\">" + name.trim() + "</strong>"));
		}
	}
	
	public static List<String> extractPeople (Entry entry) throws URISyntaxException{
		List<String> list = entityExtractor.ExtractPerson(entry.getContent());
		removeDuplicates(list);
		return list;
	}
	
	public static void highlightOrganisations (Entry entry) throws URISyntaxException{
		List<String> orgs = entityExtractor.ExtractOrganization(entry.getContent());
		for (String org : orgs){
			entry.setContent(entry.getContent().replace(org.trim(), "<strong class=\"text-danger\">" + org.trim() + "</strong>"));
		}
	}
	
	public static List<String> extractOrganisations (Entry entry) throws URISyntaxException{
		List<String> list = entityExtractor.ExtractOrganization(entry.getContent());
		removeDuplicates(list);
		return list;
	}
	
	public static void highlightLocations (Entry entry) throws URISyntaxException{
		List<String> locations = entityExtractor.ExtractLocation(entry.getContent());
		for (String l : locations){
			entry.setContent(entry.getContent().replace(l.trim(), "<strong class=\"text-danger\">" + l.trim() + "</strong>"));
		}
	}
	
	public static List<String> extractLocations (Entry entry) throws URISyntaxException{
		List<String> list = entityExtractor.ExtractLocation(entry.getContent());
		removeDuplicates(list);
		return list;
	}
	
	public static void highlightKeywords (Entry entry) throws Exception{
		String keywords = keywordExtractor.ExtractSentenceKeyword(entry.getContent(), new File("WebContent/keywords/englishStopwords.txt"));
		String[] keywordsArray = keywords.split(",");
		for (String k : keywordsArray){
			if (k.equals("strong") || k.equals("class") || k.equals("text") || k.equals("danger")){
				entry.setContent(entry.getContent().replace(" " + k.trim() + " ", "<strong class=\"text-danger\">" + " " + k.trim() + " " + "</strong>"));
			}else{
				entry.setContent(entry.getContent().replace(k.trim(), "<strong class=\"text-danger\">" + k.trim() + "</strong>"));
			}
		}
	}
	
	public static List<String> extractKeywords (Entry entry) throws Exception{
		List<String> list = new ArrayList<String>(Arrays.asList(keywordExtractor.ExtractSentenceKeyword(entry.getContent(), new File("WebContent/keywords/englishStopwords.txt")).split(",")));
		removeDuplicates(list);
		return list;
	}
	
	public static String highlightMatchedEntities(String sentence, String searchString) {
		if (!searchString.isEmpty()) {
			return sentence.replace(searchString, "<strong class=\"text-danger\">" + searchString + "</strong>");
		}
		return sentence;
	}
	
	public static String listToString(String[] list){
		return "["+ String.join(",", list) + "]";
	}
	
	private static void removeDuplicates(List<String> list){
		Set<String> set = new HashSet<String>(list);
		list.clear();
		list.addAll(set);
	}
	
}
