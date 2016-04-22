//package xpath;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

class xslt 
{
    public static void main ( String argv[] ) throws Exception 
    {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		File stylesheet = new File("search.xsl");
		//File xmlfile  = new File("cs.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		System.out.println("Enter keyword to search : ");
    	String keyword = br.readLine();
    	
    	//URL encoding
    	//keyword.replace(' ', '+'); OR
    	URLEncoder.encode(keyword, "UTF-8");
    	
		Document document = db.parse((new URL("http://sandbox.api.shopping.com/publisher/3.0/rest/GeneralSearch?apiKey=78b0db8a-0ee1-4939-a2f9-d3cd95ec0fcc&visitorUserAgent&visitorIPAddress&trackingId=7000610&keyword="+keyword+"&numItems=20=")).openStream());
		StreamSource stylesource = new StreamSource(stylesheet);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer(stylesource);
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File("test.html"));
		transformer.transform(source,result);
    }
}