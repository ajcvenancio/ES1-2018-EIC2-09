package config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import channels.Notification;

public class xmlConfig {

	private static File file = new File("config.xml");
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	public static void addPost(ArrayList<Notification> notificationList) {

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlDoc = builder.parse(file);

			NodeList root = xmlDoc.getElementsByTagName("Posts");
			for(int i =0; i < notificationList.size(); i++) {
				Element notification = xmlDoc.createElement("notification");
				notification.setAttribute("channel",notificationList.get(i).getChannel());
				notification.setAttribute("date",notificationList.get(i).getDate());
				notification.setAttribute("source",notificationList.get(i).getSource());
				notification.setAttribute("subject",notificationList.get(i).getSubject());
				notification.setAttribute("text", notificationList.get(i).getText());
				
				root.item(0).appendChild(notification);
			}
			DOMSource source = new DOMSource(xmlDoc);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			StreamResult result = new StreamResult("config.xml");
			transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}
	
	public static ArrayList<Notification> getPosts() {
		ArrayList<Notification> array = new ArrayList<>();
		
		
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document xmlDoc = builder.parse(file);
			
			NodeList posts = xmlDoc.getElementsByTagName("notification");
			
			for (int i = 0; i < posts.getLength(); i++) {
				String channel=posts.item(i).getAttributes().item(0).getNodeValue();
				String stringDate= posts.item(i).getAttributes().item(1).getNodeValue();
				Date date = getDateFromString(stringDate);
				String source=posts.item(i).getAttributes().item(2).getNodeValue();
				String subject=posts.item(i).getAttributes().item(3).getNodeValue();
				String text=posts.item(i).getAttributes().item(4).getNodeValue();
				Notification notification = new Notification(channel, date, source, subject, text);
				array.add(notification);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return array;
	}
	
	private static Date getDateFromString(String stringDate) {
		int day = Integer.parseInt(stringDate.split(" ")[0].split("-")[0]);
		int month = Integer.parseInt(stringDate.split(" ")[0].split("-")[1]);
		int year = Integer.parseInt(stringDate.split(" ")[0].split("-")[2]);
		
		int hour = Integer.parseInt(stringDate.split(" ")[1].split(":")[0]);
		int minutes =Integer.parseInt( stringDate.split(" ")[1].split(":")[1]);
		int seconds = Integer.parseInt(stringDate.split(" ")[1].split(":")[2]);
	return new Date(day,month,year,hour,minutes,seconds);
	}

	public static void removeAllPosts() {
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlDoc = builder.parse(file);

			NodeList notificationList = xmlDoc.getElementsByTagName("notification");
			System.out.println(notificationList.getLength());
			for (int i = notificationList.getLength()-1; i >= 0; i--) {
					System.out.println(i);
					Element toDelete = (Element) notificationList.item(i);
					toDelete.getParentNode().removeChild(toDelete);
			}
			
			DOMSource source = new DOMSource(xmlDoc);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "10");
			StreamResult result = new StreamResult("config.xml");
			transformer.transform(source, result);

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
//	}
	
	public static void main(String[] args) {
		Date date = new Date(1, 2, 3);
		xmlConfig x = new xmlConfig();
		/*for (int i = 0; i < 8; i++) {
			
			Notification notification = new Notification("EMAIL", date, "lele", "subject"+ i, "blablablasdfg");
			x.addPost(notification);
		}*/
//		System.out.println(getPosts());
//		removeAllPosts();
	}
}
