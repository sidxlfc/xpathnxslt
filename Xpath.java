//package xpath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;

class XPATH 
{

    static void print ( Node e ) 
    {
	
    if (e instanceof Text)
	    System.out.print(((Text) e).getData());
	else {
	    NodeList c = e.getChildNodes();
	    System.out.print("<"+e.getNodeName());
	    NamedNodeMap attributes = e.getAttributes();
	    for (int i = 0; i < attributes.getLength(); i++)
		System.out.print(" "+attributes.item(i).getNodeName()
				 +"=\""+attributes.item(i).getNodeValue()+"\"");
	    System.out.print(">");
	    for (int k = 0; k < c.getLength(); k++)
		print(c.item(k));
	    System.out.println("</"+e.getNodeName()+">");
	    System.out.println();
	}
    }

    static void eval ( String query, Document document ) throws Exception 
    {
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		NodeList result = (NodeList) xpath.evaluate(query, document, XPathConstants.NODESET);
		System.out.println("XPath query: "+query);
		for (int i = 0; i < result.getLength(); i++)
		    print(result.item(i));
		System.out.println();
    }

    
    public static void main ( String[] args ) throws Exception 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	DocumentBuilder db = dbf.newDocumentBuilder();
    	Document doc;
    	Node root;
    	int i = Integer.parseInt(br.readLine());
    	
    	switch(i)
    	{
    	case 1:
	    	//Query 1
    		System.out.println("Enter keyword to search : ");
        	String keyword = br.readLine();
	    	doc = db.parse((new URL("http://sandbox.api.shopping.com/publisher/3.0/rest/GeneralSearch?apiKey=78b0db8a-0ee1-4939-a2f9-d3cd95ec0fcc&visitorUserAgent&visitorIPAddress&trackingId=7000610&keyword="+keyword+"&numItems=20=")).openStream());
	    	root = doc.getDocumentElement();
	    	eval("//items/product[rating/rating>4.50]/fullDescription", doc);
	    	break;
    	
    	case 2:
	    	//Query 2
	    	doc = db.parse((new URL("http://sandbox.api.shopping.com/publisher/3.0/rest/GeneralSearch?apiKey=78b0db8a-0ee1-4939-a2f9-d3cd95ec0fcc&visitorUserAgent&visitorIPAddress&trackingId=7000610&keyword=sony&numItems=20=")).openStream());
	    	root = doc.getDocumentElement();
	    	eval("//items/product[contains(name, 'Sony')]/name | //items/product[contains(name, 'Sony')]/minPrice", doc);
	    	break;
    	
    	case 3:
    		//Query 3
    		doc = db.parse((new URL("http://sandbox.api.shopping.com/publisher/3.0/rest/GeneralSearch?apiKey=78b0db8a-0ee1-4939-a2f9-d3cd95ec0fcc&visitorUserAgent&visitorIPAddress&trackingId=7000610&keyword=sony&numItems=20=")).openStream());
	    	root = doc.getDocumentElement();
	    	eval("//items/product[contains(name, 'Sony') and minPrice >= 20 and maxPrice <= 4000]/name", doc);
    		break;
    	
    	default:
    			break;
    	}
    }
}