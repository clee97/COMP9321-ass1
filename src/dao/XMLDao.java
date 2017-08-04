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

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import models.Book;
import models.SearchRequest;

public class XMLDao {
	public static void main(String[] args) {
		SearchRequest request = new SearchRequest("Parallel", null, 1996, null, null);
		search(request);
	}
	public static List<Book> search(SearchRequest request) {
		String expression = "";
		List<String> filters = new ArrayList<String>();
		if (request.getTitle() != null){
			filters.add("contains(title, '" + request.getTitle() + "')");
		}
		if (request.getAuthors() != null){
			filters.add("contains(author, '" + request.getAuthors() + "')");
		}
		if (request.getYear() != null){
			filters.add("contains(year, '" + request.getYear() + "')");
		}
		if (request.getVolume() != null){
			filters.add("contains(volume, '" + request.getVolume() + "')");
		}
		if (request.getJournal() != null){
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
			File inputFile = new File("dataset/dblp.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

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
	
	public static Element getElementById(String id){
		try{
			DOMParser domp = new DOMParser();

	//		domp.parse("WebContent/dblp.xml");
//			domp.parse("../../../git/COMP9321-ass1/WebContent/dblp.xml");
			domp.parse("../git/COMP9321-ass1/dataset/dblp.xml");
			
			NodeList nl = domp.getDocument().getElementsByTagName("book");
			
			for (int i = 0; i < nl.getLength(); i++){
				Node n = nl.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE){
					Element e = (Element)n;
					if (e.getAttribute("id").equals(id)){
						return e;
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace(System.err);
		}
		return null;
	}
}
