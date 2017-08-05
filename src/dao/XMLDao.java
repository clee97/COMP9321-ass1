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

import models.Book;
import models.SearchRequest;

public class XMLDao {
	
	private static Document doc;
	
	public static void main(String[] args) {
		SearchRequest request = new SearchRequest("Sanjeev", null, null, null, null);
		search(request);
	}
	public static List<Book> search(SearchRequest request) {
		String expression = "";
		List<String> filters = new ArrayList<String>();
		if (request.getTitle() != null && !request.getTitle().isEmpty()){
			filters.add("contains(title, '" + request.getTitle() + "')");
		}
		if (request.getAuthors() != null && !request.getAuthors().isEmpty()){
			filters.add("contains(author, '" + request.getAuthors() + "')");
		}
		if (request.getYear() != null){
			filters.add("contains(year, '" + request.getYear() + "')");
		}
		if (request.getVolume() != null && !request.getVolume().isEmpty()){
			filters.add("contains(volume, '" + request.getVolume() + "')");
		}
		if (request.getJournal() != null && !request.getJournal().isEmpty() ){
			filters.add("contains(journal, '" + request.getJournal() + "')");
		}
		if (!filters.isEmpty()){
			expression = "/dblp/article[" + String.join(" and ", filters) + "]";
		}else{
			expression = "/dblp/article";
		}
		System.out.println(expression);
		List<Book> results = new ArrayList<Book>();
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
					
					Book b = new Book();
					b.getAuthors().add(e.getElementsByTagName("author").item(0).getTextContent());
					b.setTitle(e.getElementsByTagName("title").item(0).getTextContent());
					b.setJournal((e.getElementsByTagName("journal").item(0).getTextContent()));
					b.setVolume(e.getElementsByTagName("volume").item(0).getTextContent());
					b.setYear(Integer.parseInt(e.getElementsByTagName("year").item(0).getTextContent()));
					b.setPages(e.getElementsByTagName("pages").item(0).getTextContent());
					b.setUrl(e.getElementsByTagName("url").item(0).getTextContent());
					b.setEe(e.getElementsByTagName("ee").item(0).getTextContent());
					results.add(b);
					
				}
			}
		} catch(Exception e){
			e.printStackTrace(System.err);
		}
		return results;

	}
	
	private static void initXMLdoc() {
		File inputFile = new File("dataset/dblp.xml");
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
