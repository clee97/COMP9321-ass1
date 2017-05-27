package helpers;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class XMLParser {
	public static void main(String[] args) {
		Pattern p = Pattern.compile("sasa");
		System.out.println(p.matcher("hello world").find());
	}
	public static ArrayList<Element> find(String searchBy, String s){
		try{
			DOMParser domp = new DOMParser();
			ArrayList<Element> bookList = new ArrayList<Element>();
//			domp.parse("WebContent/dblp.xml");
//			domp.parse("../../../git/COMP9321-ass1/WebContent/dblp.xml");
			domp.parse("../git/COMP9321-ass1/WebContent/dblp.xml");
			
			NodeList nl = domp.getDocument().getElementsByTagName("book");
			Pattern p = Pattern.compile(s);

			for (int i = 0; i < nl.getLength(); i++){
				Node n = nl.item(i);
				
				if (n.getNodeType() == Node.ELEMENT_NODE){
					Element e = (Element)n;
					switch(searchBy){
						case "AUTHOR" : 
							if (p.matcher(e.getElementsByTagName("author").item(0).getTextContent()).find()){
								bookList.add(e);
							}
						break;
						case "TITLE" : 
							if (p.matcher(e.getElementsByTagName("title").item(0).getTextContent()).find()){
								bookList.add(e);
							}
						break;
						case "GENRE" : 
							if (p.matcher(e.getElementsByTagName("genre").item(0).getTextContent()).find()){
								bookList.add(e);
							}
						break;
						case "DESCRIPTION" : 
							if (p.matcher(e.getElementsByTagName("description").item(0).getTextContent()).find()){
								bookList.add(e);
							}
						break;
						default: bookList.add(e);
	
					}
				}
				
			}
			
			return bookList;

		} catch(Exception e){
			e.printStackTrace(System.err);
		}
		return null;
	}
	
	public static Element getElementById(String id){
		try{
			DOMParser domp = new DOMParser();

	//		domp.parse("WebContent/dblp.xml");
//			domp.parse("../../../git/COMP9321-ass1/WebContent/dblp.xml");
			domp.parse("../git/COMP9321-ass1/WebContent/dblp.xml");
			
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
