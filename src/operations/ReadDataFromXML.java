package operations;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadDataFromXML {

	private int p;
	private int o;
	private NodeList listOfPages;
	private NodeList listofObjects;
	private Node currentNode;
	private Node currentSubNode;

	private int flagPageFound;
	private int flagObjectFound;

	// private String[] objectAttributes = new string
	ArrayList<String> objectAttributes = new ArrayList<String>();

	public ArrayList<String> XMLReader(String page, String object) {

		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("..//myShopHybridFramework//FileFolder//Object-Repository.xml"));

			// normalize text representation
			doc.getDocumentElement().normalize();
			System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

			listOfPages = doc.getElementsByTagName("Pages");
			flagPageFound = 0;
			flagObjectFound = 0;
			p = 0;
			o = 0;

			while (flagPageFound == 0 && p < listOfPages.getLength()) {
				// for (p = 0; p < listOfPages.getLength(); p++) {

				currentNode = listOfPages.item(p);
				System.out.println("\nCurrent Node: " + currentNode.getNodeName());

				Element element = (Element) currentNode;
				System.out.println("Current Page: " + element.getAttribute("name"));

				if (element.getAttribute("name").equalsIgnoreCase(page)) {
					flagPageFound = 1;
					listofObjects = element.getElementsByTagName("Object");

					while (flagObjectFound == 0 && o < listofObjects.getLength()) {
						// for (o = 0; o < listofObjects.getLength(); o++) {
						currentSubNode = listofObjects.item(o);
						Element element2 = (Element) currentSubNode;

						if (element2.getTextContent().equalsIgnoreCase(object)) {
							flagObjectFound = 1;
							System.out.println("\nCurrent SubNode Text: " + element2.getTextContent());

							objectAttributes.add(element2.getAttribute("type"));
							objectAttributes.add(element2.getAttribute("value"));
							objectAttributes.add(element2.getAttribute("data"));

						}
						o++;

						/*
						 * System.out.println("\nCurrent SubNode Text: " +
						 * element2.getTextContent()); System.out.println(
						 * "Current SubNode Type: " +
						 * element2.getAttribute("type")); System.out.println(
						 * "Current SubNode Value: " +
						 * element2.getAttribute("value")); System.out.println(
						 * "Current SubNode Data: " +
						 * element2.getAttribute("data"));
						 */

					}

				}
				p++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectAttributes;
	}
}
