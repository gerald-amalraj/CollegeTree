package com.project.line;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.AdjustmentListener;
import java.net.MalformedURLException;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.apache.xmlbeans.XmlObject;

public class Bugger extends JFrame {	
	static XmlObject xmlObj = null;
	static String filePath="F:\\Oxygen Wrkspace\\BugTrackApplet\\src\\";
	
	public Bugger () throws MalformedURLException{
		prepareNetIcons();
		prepareBackgroundImages();
		
		
		Parser parseXML = new Parser();
		DrawLine currentPanel;
		MonitorToolbar toolBar;
		HeadingPanel heading;
		JScrollPane jscrollPane;
		
		
		this.setTitle("Network Tracer");
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenDim.width, screenDim.height);
				
			XmlObject xmlObj = initia();

			parseXML.parserStarts(xmlObj);
			
        	
			
			heading = new HeadingPanel(filePath);
			MonitorToolbar.setIcons(filePath);
			heading.setPreferredSize(new Dimension(this.getWidth(), 36));
			this.getContentPane().add(heading, BorderLayout.NORTH);
			
			toolBar = MonitorToolbar.getToolBar();
			currentPanel = DrawLine.getPanel();
			toolBar.setDesignedPanel(currentPanel);
			toolBar.addButtons();
			toolBar.setBorder(null);
			toolBar.setPreferredSize(new Dimension(36, this.getHeight() - 36));
			currentPanel.setToolBar(toolBar);
			this.getContentPane().add(toolBar, BorderLayout.WEST);

			jscrollPane = new JScrollPane(currentPanel,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			jscrollPane.setPreferredSize(currentPanel.getSize());
			jscrollPane.setBorder(null);
			AdjustmentListener scrollListener = new MapSymbolPanelAdjustListener();
			jscrollPane.getHorizontalScrollBar().addAdjustmentListener(
					scrollListener);
			jscrollPane.getVerticalScrollBar().addAdjustmentListener(
					scrollListener);
			
			this.getContentPane().add(jscrollPane, BorderLayout.CENTER);
			this.setVisible(true);
		
	}
	
	private void prepareNetIcons() throws MalformedURLException {
		HashMap<String, ImageIcon> stateImages = new HashMap<String, ImageIcon>();
				
		stateImages.put("college", new ImageIcon(filePath+"images/college.png"));
		
		stateImages.put("block1", new ImageIcon(filePath+"images/block1.png"));
		stateImages.put("block2", new ImageIcon(filePath+"images/block2.png"));
		stateImages.put("block3", new ImageIcon(filePath+"images/block3.png"));
		stateImages.put("block4", new ImageIcon(filePath+"images/block4.png"));
		
		stateImages.put("server1", new ImageIcon(filePath+"images/server1.png"));
		stateImages.put("server2", new ImageIcon(filePath+"images/server2.png"));
		stateImages.put("server3", new ImageIcon(filePath+"images/server3.png"));
		stateImages.put("server4", new ImageIcon(filePath+"images/server4.png"));
		stateImages.put("server5", new ImageIcon(filePath+"images/server5.png"));
		stateImages.put("server6", new ImageIcon(filePath+"images/server6.png"));
		stateImages.put("server7", new ImageIcon(filePath+"images/server7.png"));
		stateImages.put("server8", new ImageIcon(filePath+"images/server8.png"));
		
		stateImages.put("hub1", new ImageIcon(filePath+"images/hub1.png"));
		stateImages.put("hub2", new ImageIcon(filePath+"images/hub2.png"));
		stateImages.put("hub3", new ImageIcon(filePath+"images/hub3.png"));
		
		stateImages.put("sys1", new ImageIcon(filePath+"images/sys1.png"));
		stateImages.put("sys2", new ImageIcon(filePath+"images/sys2.png"));
		stateImages.put("sys3", new ImageIcon(filePath+"images/sys3.png"));
		stateImages.put("sys4", new ImageIcon(filePath+"images/sys4.png"));
		stateImages.put("sys5", new ImageIcon(filePath+"images/sys5.png"));
		stateImages.put("sys6", new ImageIcon(filePath+"images/sys6.png"));
		stateImages.put("sys7", new ImageIcon(filePath+"images/sys7.png"));
		stateImages.put("sys8", new ImageIcon(filePath+"images/sys8.png"));
		stateImages.put("sys9", new ImageIcon(filePath+"images/sys9.png"));
		stateImages.put("sys10", new ImageIcon(filePath+"images/sys10.png"));
		
		BugUIStorage.setTOPO_STATE_IMAGES(stateImages);
	}

	private void prepareBackgroundImages() throws MalformedURLException {
		HashMap<String, ImageIcon> backgroundImages = new HashMap<String, ImageIcon>();
		backgroundImages.put("hot", new ImageIcon(filePath+"backgrounds/hot.png"));
		backgroundImages.put("rainy", new ImageIcon(filePath+"backgrounds/rainy.png"));
		backgroundImages.put("random", new ImageIcon(filePath+"backgrounds/random.png"));
		backgroundImages.put("striped", new ImageIcon(filePath+"backgrounds/striped.png"));
		backgroundImages.put("tile_bricks", new ImageIcon(filePath+"backgrounds/tile_bricks.png"));

		BugUIStorage.setBACKGROUND_STATE_IMAGES(backgroundImages);
	}


	public static XmlObject initia() {
		String networkXML = "C:/opt/Bug_Tracker/BugTracker.xml";
		java.io.File inputXMLFile = new java.io.File(networkXML);
		try {
			xmlObj = XmlObject.Factory.parse(inputXMLFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlObj;
	}

	
	public static void main(String... strings) throws MalformedURLException {
		Bugger bugy = new Bugger();
		
		
		}

}