package dao;

import java.io.File;

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

import models.SearchRequest;

public class XMLDao {
	public static void main(String[] args) {
		find(null);
	}
	public static void find(SearchRequest request) {
		try{
			File inputFile = new File("dataset/dblp.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			String expression = "/dblp/book[@year='2016']";
			System.out.println(expression);
			XPath xPath =  XPathFactory.newInstance().newXPath();
			NodeList nl = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			
			for (int i = 0; i < nl.getLength(); i++){
				Node n = nl.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE){
					Element e = (Element)n;
					System.out.println("Book Key: " + e.getAttribute("key")) ;
					NodeList list = e.getChildNodes();
					for (int j = 0; j < list.getLength(); j++){
						System.out.println(list.item(j).getTextContent());
					}
					System.out.println("===============================");
				}
			}
		} catch(Exception e){
			e.printStackTrace(System.err);
		}

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
