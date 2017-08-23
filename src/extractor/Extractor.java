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
			System.out.println(keywordExtractor.ExtractSentenceKeyword("Today we continued investigating the full scope of the recent unauthorized access of the Secretary of State's online systems. A review of our online payment systems has confirmed that none of them store full credit card information. Further, we believe no credit card information has been compromised. Credit cards can be used by the public to pay annual business registration fees, elections filing fees and costs of archival materials as well as to purchase the Oregon Blue Book and other State Archives merchandise. If you are on a deadline to pay a fee, please check the Secretary of State website (http://sos.oregon.gov/) for contact information, and filing and payment options. We will continue to provide updates as more information becomes available. All available staff are working to fully restore and reinforce our website operations", new File("keywords/englishStopwords.txt")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void extractPerson (Entry entry) throws URISyntaxException{
		List<String> people = entityExtractor.ExtractPerson(entry.getContent());
		for (String name : people){
			entry.setContent(entry.getContent().replace(name.trim(), "<strong class=\"text-danger\">" + name.trim() + "</strong>"));
		}
	}
	
	public static void extractOrganisation (Entry entry) throws URISyntaxException{
		List<String> orgs = entityExtractor.ExtractOrganization(entry.getContent());
		for (String org : orgs){
			entry.setContent(entry.getContent().replace(org.trim(), "<strong class=\"text-danger\">" + org.trim() + "</strong>"));
		}
	}
	
	public static void extractLocation (Entry entry) throws URISyntaxException{
		List<String> locations = entityExtractor.ExtractLocation(entry.getContent());
		for (String l : locations){
			entry.setContent(entry.getContent().replace(l.trim(), "<strong class=\"text-danger\">" + l.trim() + "</strong>"));
		}
	}
	
	public static void extractKeyword (Entry entry) throws Exception{
		String keywords = keywordExtractor.ExtractSentenceKeyword(entry.getContent(), new File("keywords/englishStopwords.txt"));
		String[] keywordsArray = keywords.split(",");
		for (String k : keywordsArray){
			entry.setContent(entry.getContent().replace(k.trim(), "<strong class=\"text-danger\">" + k.trim() + "</strong>"));
		}

	}
}
