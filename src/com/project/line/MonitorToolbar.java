package com.project.line;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import org.apache.xmlbeans.XmlObject;


public class MonitorToolbar extends JToolBar   {
	DrawLine drw = new DrawLine();
	public static URL codePosition;
	Color bkground = new Color(131, 139, 144);
	private static Map<String, IconCollectionBean> typeMap = new LinkedHashMap<String, IconCollectionBean>(); 	
	public static MonitorToolbar toolBar ;
	private DrawLine designedPanel; 
	private final static String CONFIGURE 		= "configure";
	private final static String SAVE 			= "save";
	private final static String REFRESH 		= "refresh";
	private final static String END 			= "end";
	private final static String START 			= "start";
	private final static String BOTTOM 			= "bottom";
	Parser parseXML= new Parser();
    public static int tab=1;
	public MonitorToolbar()  {
		
    	this.setBackground(bkground);
        this.setFloatable(false);
        this.setRollover(true);
        this.setOrientation(JToolBar.VERTICAL);
        this.repaint();
        //this.addButtons(); 
    }
	
     public static void varAssign(){
    	 tab=1;
     }
	 public static void setCodePosition(URL codeBase) {
	       MonitorToolbar.codePosition = codeBase;
	    }
	 
	
	   public void addButtons() {
		   try {
			
			this.removeAll();   
	        JButton button = null;
	        
	        button = this.makeNavigationButton("start", START, "start","Start");
	        this.add(button);
	        
	        button = this.makeNavigationButton("save", SAVE, "Save","save");
	        this.add(button);
	        
	       
	        button = this.makeNavigationButton("configure", CONFIGURE, "Settings","Settings");
	        this.add(button);
	        
	        button = this.makeNavigationButton("refresh", REFRESH, "Refresh","refresh");
	        this.add(button);
	        
	        button = this.makeNavigationButton("end", END, "End","end");
	        this.add(button);
	        
	        button = this.makeNavigationButton("bottom", BOTTOM, "bottom","Bottom");
	        this.add(button);
	        
	        
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   
	   }
	   
	   public static void setIcons(String filePath) {
	    	MonitorToolbar.typeMap = new HashMap<String, IconCollectionBean>();
	        URL display_url = null;
	        URL rollOvr_url = null; 
	        Icon display_icon = null;
	        Icon rollovr_icon = null;
	        try { 
	        	IconCollectionBean icoBean = new IconCollectionBean();
		      //  display_url = new URL("images/legend_head.png");
		        display_icon = new ImageIcon(filePath+"images/legend_head.png");
		        icoBean.setDisplayIcon(display_icon);
		        MonitorToolbar.typeMap.put("start", icoBean);
	        	
		        icoBean = new IconCollectionBean();
	        	//display_url = new URL("images/save.png");
	        	//rollOvr_url = new URL("images/save_sel.png");
	            display_icon = new ImageIcon(filePath+"images/save.png");
	            rollovr_icon = new ImageIcon(filePath+"images/save_sel.png");
	            icoBean.setDisplayIcon(display_icon);
	            icoBean.setRollOvrIcon(rollovr_icon);
	            
	            MonitorToolbar.typeMap.put("save", icoBean);
	           
	            
	            icoBean = new IconCollectionBean();
	           // display_url = new URL("images/configure.png");
	           // rollOvr_url = new URL("images/configure_sel.png");
	            display_icon = new ImageIcon(filePath+"images/configure.png");
	            rollovr_icon = new ImageIcon(filePath+"images/configure_sel.png");
	            icoBean.setDisplayIcon(display_icon);
	            icoBean.setRollOvrIcon(rollovr_icon);
	            MonitorToolbar.typeMap.put("configure", icoBean);
	            	            

	            icoBean = new IconCollectionBean();
	            //display_url = new URL("images/refresh.png");
	            //rollOvr_url = new URL("images/refresh_sel.png");
	            display_icon = new ImageIcon(filePath+"images/refresh.png");
	            rollovr_icon = new ImageIcon(filePath+"images/refresh_sel.png");
	            icoBean.setDisplayIcon(display_icon);
	            icoBean.setRollOvrIcon(rollovr_icon);
	            MonitorToolbar.typeMap.put("refresh", icoBean);
	            
	            icoBean = new IconCollectionBean();
	            //display_url = new URL("images/x.png");
	            //rollOvr_url = new URL("images/x_sel.png");
	            display_icon = new ImageIcon(filePath+"images/x.png");
	            rollovr_icon = new ImageIcon(filePath+"images/x_sel.png");
	            icoBean.setDisplayIcon(display_icon);
	            icoBean.setRollOvrIcon(rollovr_icon);
	            MonitorToolbar.typeMap.put("end", icoBean);
	            
	            
	            icoBean = new IconCollectionBean();
	            //display_url = new URL("images/legend_bottom.png");
	            display_icon = new ImageIcon(filePath+"images/legend_bottom.png");
	            icoBean.setDisplayIcon(display_icon);
	            MonitorToolbar.typeMap.put("bottom", icoBean);
	            
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	   }
	   
	   
		   private JButton makeNavigationButton(String imgName, String actionCommand, String toolTipText, String altText) {
		    	IconCollectionBean icoBean = MonitorToolbar.typeMap.get(imgName.trim());
		        JButton button = new JButton();
		        button.setName(imgName.trim());
		        button.setActionCommand(actionCommand);
		        button.setToolTipText(toolTipText); 
		        button.setIcon(icoBean.getDisplayIcon());
		        button.setRolloverIcon(icoBean.getRollOvrIcon());
		       
		        button.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		             if(e.getActionCommand()=="save") {
		            	toolbarListener tool = new toolbarListener();
		            	tool.toolclick();
		            	 JOptionPane.showMessageDialog(null, "Saved", "Message", JOptionPane.INFORMATION_MESSAGE);
		             }
		             else if(e.getActionCommand()=="configure"){
		            	 
		            	 if(tab == 1){
		            	 new Configuration();
		            	 tab =2;
		            	 }
		            	 else {
		            		 JOptionPane.showMessageDialog(null, "Tab Already Opened", "Message", JOptionPane.ERROR_MESSAGE);	
		            	 }
		             }
		            	
		             else if(e.getActionCommand()=="refresh"){
		            	ServerLinkMaker link = new ServerLinkMaker();
		            	XmlObject xmlObj;
		            	try {
							xmlObj = link.callMethod();
							parseXML.parserStarts(xmlObj);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						//Bugger bug = new Bugger();
						//bug.init();
						  //DrawLine drw = new DrawLine();
						 // drw.ElementsCreation();
		            	  DrawLine.getPanel().repaint();
		            	 //System.out.println(("<applet  base= / code=com.project.line.Bugger.class name=Bug_Tracker archive =BugT.jar>")); 

		             }
		            	
		            }
		        });

		        button.setContentAreaFilled(false);
		        button.setBorderPainted(false);
		        button.setBorder(null);
		        button.setIgnoreRepaint(false);
		        button.setMaximumSize(new Dimension(32, 32));
		        return button;
		   }
	    
	 public static MonitorToolbar getToolBar() {
	        if(MonitorToolbar.toolBar == null)
	        	MonitorToolbar.toolBar = new MonitorToolbar();
	        
	        return MonitorToolbar.toolBar;
	    }
	 
	 public void setDesignedPanel(DrawLine pDesignedPanel) {
			this.designedPanel = pDesignedPanel;
		}
	
}
