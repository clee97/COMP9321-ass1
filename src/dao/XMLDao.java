package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.stanford.nlp.ling.CoreAnnotations.FirstChildAnnotation;
import extractor.Extractor;
import models.AdvancedSearchRequest;
import models.Entry;
import models.SearchRequest;

public class XMLDao {
	
	private static Document doc;
	
	public XMLDao() {
		initXMLdoc();
	}
	
	public static void main(String[] args) {
		XMLDao dao = new XMLDao();
		System.out.println("Randy Weist".contains("Randy Weist"));
	}
	
	/**
	 * 
	 * @param request (search strings will be stored here)
	 * @return returns paginated results 10 entries per page
	 */
	public List<List<Entry>> search(SearchRequest request) {
		String expression = searchFilter(request);
		List<Entry> results = new ArrayList<Entry>();
		try{
			XPath xPath =  XPathFactory.newInstance().newXPath();
			NodeList nl = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			
			for (int i = 0; i < nl.getLength(); i++){
				Node n = nl.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE){
					Element e = (Element)n;
					/*debug stuff*/
					System.out.println(e.getAttribute("_address"));
					System.out.println(e.getElementsByTagName("agency").item(0).getTextContent()) ;
					System.out.println(e.getElementsByTagName("headline").item(0).getTextContent()) ;
					System.out.println(e.getElementsByTagName("publish_date").item(0).getTextContent()) ;
					System.out.println(e.getElementsByTagName("city").item(0).getTextContent()) ;
					System.out.println("===============================");
					
					Entry entry = new Entry();
					entry.setAddress(e.getAttribute("_address"));
					entry.setAgency(e.getElementsByTagName("agency").item(0).getTextContent());
					entry.setHeadline(e.getElementsByTagName("headline").item(0).getTextContent());
					entry.setDate(e.getElementsByTagName("publish_date").item(0).getTextContent());
					entry.setCity(e.getElementsByTagName("city").item(0).getTextContent());
					if (e.getElementsByTagName("entered_by").getLength() > 0)
						entry.setEnteredBy(e.getElementsByTagName("entered_by").item(0).getTextContent());
					else
						entry.setEnteredBy("Anonymous");
					
					if (e.getElementsByTagName("content").getLength() > 0)
						entry.setContent(e.getElementsByTagName("content").item(0).getTextContent().substring(0, e.getElementsByTagName("content").item(0).getTextContent().length()/4) + "<strong class=\"text-danger\">....... CLICK TITLE TO READ MORE</strong>");
					results.add(entry);
					
				}
			}
		} catch(Exception e){
			e.printStackTrace(System.err);
		}
		return paginate(results, 10);

	}
	
	/**
	 * searches for keywords, organisations, locations and people in the content 
	 * @return returns entries that match the searched terms
	 */
	public List<List<Entry>> advancedSearch(AdvancedSearchRequest request) {
		String expression = advancedSearchFilter(request);
		List<Entry> results = new ArrayList<Entry>();
		try{
			XPath xPath =  XPathFactory.newInstance().newXPath();
			NodeList nl = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			
			for (int i = 0; i < nl.getLength(); i++){
				Node n = nl.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE){
					Element e = (Element)n;
					/*debug stuff*/
					System.out.println(e.getAttribute("_address"));
					System.out.println(e.getElementsByTagName("agency").item(0).getTextContent()) ;
					System.out.println(e.getElementsByTagName("headline").item(0).getTextContent()) ;
					System.out.println(e.getElementsByTagName("publish_date").item(0).getTextContent()) ;
					System.out.println(e.getElementsByTagName("city").item(0).getTextContent()) ;
					System.out.println("===============================");
					
					Entry entry = new Entry();
					entry.setAddress(e.getAttribute("_address"));
					entry.setAgency(e.getElementsByTagName("agency").item(0).getTextContent());
					entry.setHeadline(e.getElementsByTagName("headline").item(0).getTextContent());
					entry.setDate(e.getElementsByTagName("publish_date").item(0).getTextContent());
					entry.setCity(e.getElementsByTagName("city").item(0).getTextContent());
					entry.setContent(e.getElementsByTagName("content").item(0).getTextContent());
					if (e.getElementsByTagName("entered_by").getLength() > 0)
						entry.setEnteredBy(e.getElementsByTagName("entered_by").item(0).getTextContent());
					else
						entry.setEnteredBy("Anonymous");
					
					String[] keywords = Extractor.extractKeywords(entry);
					List<String> people = Extractor.extractPeople(entry);
					List<String> organisations = Extractor.extractOrganisations(entry);
					List<String> locations = Extractor.extractLocations(entry);
					
					boolean isPartOfKeywords = isSubstringOf(keywords, request.getKeyword());
					boolean isPartOfPeople = isSubstringOf(people, request.getPerson());
					boolean isPartOfOrganisations = isSubstringOf(organisations, request.getOrganisation());
					boolean isPartOfLocations = isSubstringOf(locations, request.getLocation());
					
					entry.setContent(e.getElementsByTagName("content").item(0).getTextContent().substring(0, e.getElementsByTagName("content").item(0).getTextContent().length()/4) + "<strong class=\"text-danger\">....... CLICK TITLE TO READ MORE</strong>");
					
					System.out.println(isPartOfKeywords);
					System.out.println(isPartOfPeople);
					System.out.println(isPartOfOrganisations);
					System.out.println(isPartOfLocations);
					
					if (isPartOfKeywords && isPartOfPeople && isPartOfLocations && isPartOfOrganisations) {
						String keywordsString = Extractor.highlightMatchedEntities(keywords.toString(), request.getKeyword());
						String peopleString = Extractor.highlightMatchedEntities(people.toString(), request.getPerson());
						String organisationsString = Extractor.highlightMatchedEntities(organisations.toString(), request.getOrganisation());
						String locationsString = Extractor.highlightMatchedEntities(locations.toString(), request.getLocation());
						System.out.println(people.toString());
						entry.setContent(entry.getContent() + "\n\nKeywords : " + keywordsString
								+ "\n\nPeople : " + peopleString
								+ "\n\nOrganisations : " + organisationsString
								+ "\n\nLocations : " + locationsString);
						
						results.add(entry);
					}
					
				}
			}
		} catch(Exception e){
			e.printStackTrace(System.err);
		}
		return paginate(results, 10);
	}
	/**
	 * @param list (list to be checked)
	 * @param searchString (search string that will be checked inside the list)
	 * @return returns whether or not the searchString is contained in any element of the list
	 */
	private boolean isSubstringOf(List<String> list, String searchString) {
		for (String s : list) {
			if (s.contains(searchString)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param list (list to be checked)
	 * @param searchString (search string that will be checked inside the list)
	 * @return returns whether or not the searchString is contained in any element of the list
	 */
	private boolean isSubstringOf(String[] list, String searchString) {
		for (String s : list) {
			if (s.contains(searchString)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param address (specific address of the entry to search for)
	 * @return returns the entry that has that address
	 */
	public Entry searchByAddress(String address) {
		Entry entry = null;
		try{
			XPath xPath =  XPathFactory.newInstance().newXPath();
			NodeList nl = (NodeList) xPath.compile("/response/row/row[contains(@_address, '" + address + "')]").evaluate(doc, XPathConstants.NODESET);
			Element e = (Element)nl.item(0);
			
			System.out.println(e.getAttribute("_address"));
			System.out.println(e.getElementsByTagName("agency").item(0).getTextContent()) ;
			System.out.println(e.getElementsByTagName("headline").item(0).getTextContent()) ;
			System.out.println(e.getElementsByTagName("publish_date").item(0).getTextContent()) ;
			System.out.println(e.getElementsByTagName("city").item(0).getTextContent()) ;
			System.out.println(e.getElementsByTagName("entered_by").item(0).getTextContent()) ;
			System.out.println("===============================");
			
			entry = new Entry();
			entry.setAddress(e.getAttribute("_address"));
			entry.setAgency(e.getElementsByTagName("agency").item(0).getTextContent());
			entry.setHeadline(e.getElementsByTagName("headline").item(0).getTextContent());
			entry.setDate(e.getElementsByTagName("publish_date").item(0).getTextContent());
			entry.setCity(e.getElementsByTagName("city").item(0).getTextContent());
			if (e.getElementsByTagName("entered_by").getLength() > 0)
				entry.setEnteredBy(e.getElementsByTagName("entered_by").item(0).getTextContent());
			else
				entry.setEnteredBy("Anonymous");
			if (e.getElementsByTagName("content").getLength() > 0)
				entry.setContent(e.getElementsByTagName("content").item(0).getTextContent());
			
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
		return entry;
	}
	
	/**
	 * @param count (count many entries to display)
	 * @return returns count many random entries to be displayed on the home screen
	 */
	public List<Entry> randomise(Integer count){
		List<Entry> results = new ArrayList<Entry>();
		List<Entry> randomList = new ArrayList<Entry>();
		try{
			XPath xPath =  XPathFactory.newInstance().newXPath();
			NodeList nl = (NodeList) xPath.compile("/response/row/row").evaluate(doc, XPathConstants.NODESET);
			
			for (int i = 0; i < nl.getLength(); i++){
				Node n = nl.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE){
					Element e = (Element)n;
					
					Entry entry = new Entry();
					entry.setAddress(e.getAttribute("_address"));
					entry.setAgency(e.getElementsByTagName("agency").item(0).getTextContent());
					entry.setHeadline(e.getElementsByTagName("headline").item(0).getTextContent());
					entry.setDate(e.getElementsByTagName("publish_date").item(0).getTextContent());
					entry.setCity(e.getElementsByTagName("city").item(0).getTextContent());
					if (e.getElementsByTagName("entered_by").getLength() > 0)
						entry.setEnteredBy(e.getElementsByTagName("entered_by").item(0).getTextContent());
					else
						entry.setEnteredBy("Anonymous");
					if (e.getElementsByTagName("content").getLength() > 0)
						entry.setContent(e.getElementsByTagName("content").item(0).getTextContent().substring(0, e.getElementsByTagName("content").item(0).getTextContent().length()/4) + "<strong class=\"text-danger\">....... CLICK TITLE TO READ MORE</strong>");
					results.add(entry);
					
				}
				
			}
			Collections.shuffle(results);
			if (count > results.size()) count = results.size();
			randomList = results.subList(0, count);
			
			for (Entry en : randomList){
				System.out.println(en.getAgency());
				System.out.println(en.getHeadline());
				System.out.println(en.getDate());
				System.out.println(en.getCity());
				System.out.println("========================================");
			}

		} catch(Exception e){
			e.printStackTrace(System.err);
		}
		return randomList;
	}
	/**
	 * Builds an expression for XPath based on search request data
	 */
	private String searchFilter(SearchRequest request){
		String expression = "";
		List<String> filters = new ArrayList<String>();
		if (request.getAgency() != null && !request.getAgency().isEmpty()){
			filters.add("contains(agency, '" + request.getAgency() + "')");
		}
		if (request.getHeadline() != null && !request.getHeadline().isEmpty()){
			filters.add("contains(headline, '" + request.getHeadline() + "')");
		}
		if (request.getDate() != null && !request.getDate().isEmpty()){
			filters.add("contains(publish_date, '" + request.getDate() + "')");
		}
		if (request.getCity() != null && !request.getCity().isEmpty()){
			filters.add("contains(city, '" + request.getCity() + "')");
		}
		if (request.getContent() != null && !request.getContent().isEmpty() ){
			filters.add("contains(content, '" + request.getContent() + "')");
		}
		if (!filters.isEmpty()){
			expression = "/response/row/row[" + String.join(" and ", filters) + "]";
		}else{
			expression = "/response/row/row";
		}
		System.out.println(expression);
		return expression;
	}
	
	private String advancedSearchFilter(AdvancedSearchRequest request){
		String expression = "";
		List<String> filters = new ArrayList<String>();
		if (request.getKeyword() != null && !request.getKeyword().isEmpty()){
			filters.add("contains(content, '" + request.getKeyword() + "')");
		}
		if (request.getPerson() != null && !request.getPerson().isEmpty()){
			filters.add("contains(content, '" + request.getPerson() + "')");
		}
		if (request.getOrganisation() != null && !request.getOrganisation().isEmpty()){
			filters.add("contains(content, '" + request.getOrganisation() + "')");
		}
		if (request.getLocation() != null && !request.getLocation().isEmpty()){
			filters.add("contains(content, '" + request.getLocation() + "')");
		}
		if (!filters.isEmpty()){
			expression = "/response/row/row[" + String.join(" and ", filters) + "]";
		}else{
			expression = "/response/row/row";
		}
		return expression;
	}
	
	private List<List<Entry>> paginate(List<Entry> list, Integer pageSize) {
	    if (list == null){
	        return Collections.emptyList();
	    }
	   
	    if (pageSize == null || pageSize <= 0 || pageSize > list.size()){
	        pageSize = list.size();
	    }
	    int numPages = (int) Math.ceil((double)list.size() / (double)pageSize);
	    
	    List<List<Entry>> pages = new ArrayList<List<Entry>>(numPages);
	    
	    for (int pageNum = 0; pageNum < numPages;){
	        pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
	    }
	    return pages;
	}
	
	private void initXMLdoc() {
		File inputFile = new File("WebContent/dataset/rows.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(inputFile);
			document.getDocumentElement().normalize();
			doc = document;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
