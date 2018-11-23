package com.project.line;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeadingPanel extends JPanel {
	
	Color bkground = new Color(255, 255, 255);
	private static URL codePosition;
	private Image bgImage;
	 
	public HeadingPanel() {
	        this.setBackground(this.bkground);
	 }
	 public HeadingPanel(Image img) {
			
	        this.bgImage = img;
	        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
	        this.setSize(screenDim.width, screenDim.height);
	        setLayout(new BorderLayout());
	        setPreferredSize(screenDim);
	        setMinimumSize(screenDim);
	        setMaximumSize(screenDim);
	      }  
	 
	  public HeadingPanel(String imgURL) throws MalformedURLException {
	    	//this(new ImageIcon(imgURL)); 
	    	JLabel iCoLabel = new JLabel("");
			iCoLabel.setIcon(new ImageIcon(imgURL+"images/header_sprng1.gif"));
	    	this.add(iCoLabel, BorderLayout.EAST);
	    }
	  
	  public static void setCodePosition(URL codeBase) {
	       HeadingPanel.codePosition = codeBase; 
	    } 
	  
	  public void paintComponent(Graphics g) {
		//  System.out.println("@@@@@@@@@@@@@@@@@ "+bgImage);
	        g.drawImage(bgImage, 0, 0, null);
	      } 
}
