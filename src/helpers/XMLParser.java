package helpers;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class XMLParser {
	public static void main(String[] args) {
		find("s");
	}
	public static ArrayList<Element> find(String s){
		try{
			DOMParser domp = new DOMParser();
			ArrayList<Element> bookList = new ArrayList<Element>();
//			domp.parse("WebContent/dblp.xml");
			domp.parse("../../../git/COMP9321-ass1/WebContent/dblp.xml");
			NodeList nl = domp.getDocument().getElementsByTagName("book");
			
			for (int i = 0; i < nl.getLength(); i++){
				Node n = nl.item(i);
				
				if (n.getNodeType() == Node.ELEMENT_NODE){
					bookList.add((Element)n);
				}
			}
			return bookList;

		} catch(Exception e){
			e.printStackTrace(System.err);
		}
		return null;
	}
}
