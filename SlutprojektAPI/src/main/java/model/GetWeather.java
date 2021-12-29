package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GetWeather {

	public static void getTheWeather(WeatherBean wBean) throws IOException {

		// API call URL (country and city added)
		String URLtoSend = "http://api.openweathermap.org/data/2.5/weather?q=" + wBean.getCity() + ","
				+ wBean.getCountry() + "&APPID=e45cddac234b8f33560a0c64c98f41f9&mode=xml";
		
		// to print in a browser
		System.out.println(URLtoSend);

		// URL to send
		URL line_api_url = new URL(URLtoSend);
		
		// Connection to sent the Get request
		HttpURLConnection linec = (HttpURLConnection) line_api_url.openConnection();
		linec.setDoInput(true);
		linec.setDoOutput(true);
		linec.setRequestMethod("GET");

		// Buffer reader to read the response from API
	BufferedReader in = new BufferedReader(new InputStreamReader(linec.getInputStream()));

		String inputLine;

		String ApiResponse = "";

		while ((inputLine = in.readLine()) != null) {

			ApiResponse += inputLine;
		}

		in.close();
		
		// XMLdoc 
		Document doc = convertStringToXMLDocument(ApiResponse);

		doc.getDocumentElement().normalize();
		
		// A list that gets everything undercloud tag
		NodeList nList = doc.getElementsByTagName("clouds");
		
		// Loop through the contect
		for (int j = 0; j < nList.getLength(); j++) {

			Node node = nList.item(j);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;

				String XMLclouds = eElement.getAttribute("name");
				wBean.setCloud(XMLclouds);
			}
		}

		nList = doc.getElementsByTagName("lastupdate");

		for (int j = 0; j < nList.getLength(); j++) {

			Node node = nList.item(j);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;

				String XMLdateandtime = eElement.getAttribute("value");
				wBean.setDateAndTime(XMLdateandtime);

			}
		}
		
		nList = doc.getElementsByTagName("speed");

		for (int j = 0; j < nList.getLength(); j++) {

			Node node = nList.item(j);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;

				String XMLwind = eElement.getAttribute("name");
				wBean.setWind(XMLwind);

			}
		}
	}

	// XML doc to string 
	private static Document convertStringToXMLDocument(String xmlString) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		// Api to obtain DOM document instans
		DocumentBuilder builder = null;
		try {
			
			//Creates Document builder
			builder = factory.newDocumentBuilder();
			
			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
