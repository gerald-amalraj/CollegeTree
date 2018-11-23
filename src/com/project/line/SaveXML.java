package com.project.line;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class SaveXML {
	
	public SaveXML() throws XMLStreamException, IOException {
	}

	public void buildXmlFile(String xmlFileName,Rectangle[] r,ArrayList<JLabel> arrayObj,String[] str) {

		try {
			 int width  = 600;
			 int height = 600;
			
			File file = new File(xmlFileName);
			XMLStreamWriter writer = null;
			XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
			writer = xmlof.createXMLStreamWriter(new FileWriter(file));

			writer.writeStartDocument("1.0");
			writer.writeCharacters("\n");

			writer.writeStartElement("network");
			writer.writeCharacters("\n\t");

			writer.writeStartElement("containers");
			writer.writeCharacters("\n\t\t");

			writer.writeStartElement("container");
			writer.writeAttribute("xml_id", "1");
			writer.writeAttribute("id", "0");
			writer.writeAttribute("name", "BugTracker");
			writer.writeAttribute("type", "bug_tracker");
			writer.writeAttribute("x", "300");
			writer.writeAttribute("y", "300");

			writer.writeCharacters("\n\t\t\t");
			writer.writeStartElement("nodes");
			writer.writeCharacters("\n\t\t\t\t");

			String userName = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost/hiberdemo";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, userName,
					password);
			Statement stat = conn.createStatement();

			ResultSet rset = stat.executeQuery("select * from users");
			System.out.println("Database connection established");
			// System.out.println (rset.getString("1"));
			
			/*String wid = String.valueOf(position.nextInt(width));
			System.out.println("%%%%%%%%%%%%%%%% "+wid);
			
			String hig = String.valueOf(position.nextInt(height));
			System.out.println("^^^^^^^^^^^^^^^^^^^^^ "+hig);*/
			int i=0;
			while (rset.next()) {
				 Random position = new Random();
				
				writer.writeStartElement("node");
				writer.writeAttribute("xml_id", "2");
				writer.writeAttribute("id", "3162");
				
				writer.writeAttribute("name", arrayObj.get(i).getText());
				writer.writeAttribute("type", str[i]);
				writer.writeAttribute("x", String.valueOf((int)(r[i].getX())));
				writer.writeAttribute("y",String.valueOf((int)r[i].getY()));
				i++;
				writer.writeEndElement();
				writer.writeCharacters("\n\t\t\t\t");
				
			}
			rset.close();

			ResultSet rset1 = stat.executeQuery("select * from elements");
			 
			
			while (rset1.next()) {
				//Random position1 = new Random();
				writer.writeStartElement("node");
				writer.writeAttribute("xml_id", "3");
				writer.writeAttribute("id", "3163");
				writer.writeAttribute("name", arrayObj.get(i).getText());
				writer.writeAttribute("type", str[i]);
				writer.writeAttribute("x",String.valueOf((int)(r[i].getX())));
				writer.writeAttribute("y", String.valueOf((int)r[i].getY()));
				i++;
				writer.writeEndElement();
				writer.writeCharacters("\n\t\t\t\t");
				
			}
			
			
			writer.writeCharacters("\n\t\t\t\t");
			writer.writeEndElement();
			writer.writeCharacters("\n\t\t\t\t");
			writer.writeStartElement("links");
			writer.writeCharacters("\n\t\t\t\t");
			rset1.close();
			ResultSet rset2 = stat
					.executeQuery("SELECT users.name, elements.elementName FROM users INNER JOIN elements ON users.id=elements.id");
			i=1;
			while (rset2.next()) {
				writer.writeStartElement("link");
				writer.writeAttribute("xml_id", String.valueOf(i));
				writer.writeAttribute("name", rset2.getString("users.name")
						+ " is connected to "
						+ rset2.getString("elements.elementName"));
				writer.writeCharacters("\n\t\t\t\t\t");

				writer.writeStartElement("start");
				writer.writeAttribute("xml_id", String.valueOf(i));
				writer.writeAttribute("ip", rset2.getString("users.name"));
				writer.writeEndElement();
				writer.writeCharacters("\n\t\t\t\t\t");
				writer.writeStartElement("end");
				writer.writeAttribute("xml_id", String.valueOf(i));
				writer.writeAttribute("ip", rset2
						.getString("elements.elementName"));
				writer.writeEndElement();
				writer.writeCharacters("\n\t\t\t\t");
				writer.writeEndElement();
				i++;
			}
			rset2.close();
			writer.writeEndElement();
			writer.writeCharacters("\n\t\t");
			writer.writeEndElement();
			writer.writeCharacters("\t\n\t");
			writer.writeEndElement();
			writer.writeCharacters("\n");
			writer.writeEndDocument();
			
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void main(String args[]) throws XMLStreamException,
			IOException {

		new SaveXML();
	}

}
