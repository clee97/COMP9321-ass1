package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import models.Entry;
import models.SearchRequest;

public class XMLDao {
	
	private static Document doc;
	
	public static void main(String[] args) {
		SearchRequest request = new SearchRequest("Department", null, null, null, null);
		XMLDao dao = new XMLDao();
		//List<Entry> pages = dao.search(request, 1);

		//dao.randomise(10);
		//dao.searchByAddress("http://data.oregon.gov/resource/j8eb-8um2/723");
	}
	
	public XMLDao() {
		initXMLdoc();
	}
	
	public List<List<Entry>> search(SearchRequest request) {
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
					entry.setEnteredBy(e.getElementsByTagName("entered_by").item(0).getTextContent());
					if (e.getElementsByTagName("content").getLength() > 0)entry.setContent(e.getElementsByTagName("content").item(0).getTextContent());
					results.add(entry);
					
				}
			}
		} catch(Exception e){
			e.printStackTrace(System.err);
		}
		return paginate(results, 10);

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
			entry.setEnteredBy(e.getElementsByTagName("entered_by").item(0).getTextContent());
			if (e.getElementsByTagName("content").getLength() > 0)entry.setContent(e.getElementsByTagName("content").item(0).getTextContent());
			
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
		return entry;
	}
	
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
					if (e.getElementsByTagName("content").getLength() > 0)entry.setContent(e.getElementsByTagName("content").item(0).getTextContent());
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
	
	private void initXMLdoc() {
		File inputFile = new File("dataset/rows.xml");
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
