package com.ca.field.xml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Test1 extends DefaultHandler{

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

        Object o;
        String s;
        String xString = "";
        String file="resources\\login.xml";

        // read .json file to fill up jString (holding the json we want to test)
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
               // process the line.
            	xString += line;
            }
        }
        System.out.println("done");
        
        Document xml = loadXMLFromString(xString);
        
        xml.getDocumentElement().normalize();
        
    	System.out.println("Root element :" + xml.getDocumentElement().getNodeName());
    	
    	// NodeList nList = xml.getElementsByTagName("nkey");
    	// Node nNode = nList.item(0);
    	// Element eElement = (Element) nNode;   	
    	
    	String nkey = xml.getElementsByTagName("nkey").item(0).getTextContent();
    	
    	file="resources\\rule_log.xml";
    	xString="";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
               // process the line.
            	xString += line;
            }
        }
        
        xml = loadXMLFromString(xString);
        xml.getDocumentElement().normalize();
        
        System.out.println("Root element :" + xml.getDocumentElement().getNodeName());
        
        NodeList nList = xml.getElementsByTagName("probe");
        Node nNode;
        
        for (int i=0;i<nList.getLength();i++)
        {
        	nNode = nList.item(i);
        	
        	System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) nNode;
                eElement = (Element) nNode; 
                // System.out.println(+eElement.getAttribute("name"));
                System.out.println("Probe : "
                        + eElement
                        .getElementsByTagName("name")
                        .item(0)
                        .getTextContent()
                        + " ctime : "
                                + eElement
                                .getElementsByTagName("ctime")
                                .item(0)
                                .getTextContent()
                        + " ptime : "
                                + eElement
                                .getElementsByTagName("ptime")
                                .item(0)
                                .getTextContent());
                            
                
                }
 /*       	
        	System.out.println("element ="+eElement.getTagName());
        	
        	Node nName = eElement.getAttributeNode("name");
        	String name = nName.getNodeValue();
        	
        	String cTime = eElement.getAttribute("ctime");
        	
        	System.out.println("Name="+name+" value ctime="+cTime);
        	*/
        }
        // Node nNode = nList.item(0);
    	// Element eElement = (Element) nNode;   
        
       /* 
        // obtain and configure a SAX based parser  
       SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        
       // obtain object for SAX parser
       SAXParser saxParser = saxParserFactory.newSAXParser(); 
       */
    	
    	
       
	}
	
	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return builder.parse(is);
	}
}