package dao;

import java.io.File;
import java.util.ArrayList;
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

import models.Entry;
import models.SearchRequest;

public class XMLDao {
	
	private static Document doc;
	
	public static void main(String[] args) {
		SearchRequest request = new SearchRequest("Sanjeev", null, null, null, null);
		XMLDao dao = new XMLDao();
		dao.search(request);
	}
	
	public XMLDao() {
		initXMLdoc();
	}
	
	public List<Entry> search(SearchRequest request) {
		String expression = "";
		List<String> filters = new ArrayList<String>();
		if (request.getAgency() != null && !request.getAgency().isEmpty()){
			filters.add("contains(agency, '" + request.getAgency() + "')");
		}
		if (request.getHeadline() != null && !request.getHeadline().isEmpty()){
			filters.add("contains(headline, '" + request.getHeadline() + "')");
		}
		if (request.getDate() != null && !request.getDate().isEmpty()){
			filters.add("contains(year, '" + request.getDate() + "')");
		}
		if (request.getCity() != null && !request.getCity().isEmpty()){
			filters.add("contains(city, '" + request.getCity() + "')");
		}
		if (request.getContent() != null && !request.getContent().isEmpty() ){
			filters.add("contains(content, '" + request.getContent() + "')");
		}
		if (!filters.isEmpty()){
			expression = "/dblp/article[" + String.join(" and ", filters) + "]";
		}else{
			expression = "/dblp/article";
		}
		System.out.println(expression);
		List<Entry> results = new ArrayList<Entry>();
		try{
			initXMLdoc();
			XPath xPath =  XPathFactory.newInstance().newXPath();
			NodeList nl = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			
			for (int i = 0; i < nl.getLength(); i++){
				Node n = nl.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE){
					Element e = (Element)n;
					/*debug stuff*/
					System.out.println("Book Key: " + e.getAttribute("key")) ;
					System.out.println(e.getElementsByTagName("author").item(0).getTextContent()) ;
					System.out.println(e.getElementsByTagName("title").item(0).getTextContent()) ;
					System.out.println(e.getElementsByTagName("journal").item(0).getTextContent()) ;
					System.out.println(e.getElementsByTagName("volume").item(0).getTextContent()) ;
					System.out.println(e.getElementsByTagName("year").item(0).getTextContent()) ;
					System.out.println(e.getElementsByTagName("pages").item(0).getTextContent()) ;
					System.out.println(e.getElementsByTagName("url").item(0).getTextContent()) ;
					System.out.println(e.getElementsByTagName("ee").item(0).getTextContent()) ;
					System.out.println("===============================");
					
					Entry entry = new Entry();
					entry.setAgency(e.getElementsByTagName("agency").item(0).getTextContent());
					entry.setHeadline(e.getElementsByTagName("headline").item(0).getTextContent());
					entry.setDate(e.getElementsByTagName("date").item(0).getTextContent());
					entry.setCity(e.getElementsByTagName("city").item(0).getTextContent());
					entry.setContent(e.getElementsByTagName("content").item(0).getTextContent());
					results.add(entry);
					
				}
			}
		} catch(Exception e){
			e.printStackTrace(System.err);
		}
		return results;

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
