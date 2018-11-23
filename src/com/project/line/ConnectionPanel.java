package com.project.line;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


class ConnectionPanel extends DrawLine {
	JLabel label1, label2, label3, usr1, usr2, usr3, usr4, usr5, usr6;
	JLabel[] labels;
	JFrame f1;
	JLabel selectedLabel;
	Image backgroundImage;
	//ArrayList<JLabel> arrayObj = new ArrayList<JLabel>();
	
	public ConnectionPanel() {
		try{
		String xmlChk = new String("C:/opt1/BugTracker.xml");
		//SaveXML create = new SaveXML();
		//create.buildXmlFile(xmlChk,r1,arrayObj,str);
		}
		catch(Exception e){
		
		}
	}

	

	/*public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(backgroundImage, 200, 200, null);

	}*/

}