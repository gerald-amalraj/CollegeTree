package com.project.line;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputAdapter;

class DrawLine extends JPanel{
	JLabel label1;
	public static ArrayList<JLabel> arrayObj = new ArrayList<JLabel>();
	JLabel selectedLabel;
	Point[] p;
	JMenuItem m1, m2, m3, m4;
	public static Rectangle r1[]=null;
	public static String str[]=null;
	toolbarListener tool ;
	static int kl=1,lk=1;
	Parser parseXML= new Parser();
	 private static final DrawLine panel = new DrawLine();

		ComponentMover mover = new ComponentMover();
	   protected String imgLocation = "";  
	   protected DrawComponent currentComponent;
	   ImageIcon icon=null;
	   MonitorToolbar toolBar;
	   DrawLine() {
		    super();
	        this.setLayout(null);
	        this.setPreferredSize(new Dimension((int) 4500, (int) 4500));
	        this.setOpaque(true); 
	        this.setBounds(0, 0, this.getSize().width, this.getSize().height);
	        if(kl==1){
	        ElementsCreation();
	       kl++;
	   }
		
	}
	
	  public static DrawLine getPanel(){
	       return panel;
	       
	    } 
	  
	 
	public void ElementsCreation(){
				try{
					
		Icon genericImage=null;
		Iterator iter = BugUIStorage.getNODES_MAPPING().entrySet().iterator();
	 	while(iter.hasNext()){
			Map.Entry<String, String> ent = (Map.Entry<String,String>)iter.next();
	         		
			String key = ent.getKey();
			String[] value = ent.getValue().split("\\$");	
			
			genericImage = new ImageIcon(BugUIStorage.getTOPO_STATE_IMAGES().get(value[0]).getImage());
			label1 = new JLabel(key, genericImage, JLabel.CENTER);
			
			if(value[0].equalsIgnoreCase("college")){
				label1.setBounds(Integer.parseInt(value[1]),Integer.parseInt(value[2]), 150,150);
			}else{
				label1.setBounds(Integer.parseInt(value[1]),Integer.parseInt(value[2]), 85,75 );	
			}
			
			
			arrayObj.add(label1);
			
			r1=new Rectangle[arrayObj.size()];
			str=new String[arrayObj.size()];
			
			BugUIStorage.setLabelObj(key, label1);
			
	 	
			
		}
	 	//repaint();
				}
				catch(Exception e){
					System.out.println(e);
			}

				//System.out.println("Array Size : : :"+arrayObj.size());
		Iterator it = arrayObj.iterator();
		JPanel pane = new JPanel();
		while(it.hasNext()){
			
			JLabel jLab = (JLabel) it.next();
			jLab.setHorizontalTextPosition(SwingConstants.CENTER);
			jLab.setVerticalTextPosition(SwingConstants.BOTTOM);
			add(jLab);
			
			
		}
		
	  repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int i, j;
		
			Iterator iter = BugUIStorage.getLINKS_MAPPING().entrySet().iterator();
			while(iter.hasNext()){
				
				Map.Entry<String, String> ent = (Map.Entry<String, String>)iter.next();
				
				String value = ent.getValue();
				//System.out.println(value);
				String start = value.substring(0, value.indexOf("is")).trim();
				String end   = value.substring(value.indexOf("to")+2, value.length()).trim();
				JLabel startLab = BugUIStorage.getLabelObj().get(start);
				JLabel endLab = BugUIStorage.getLabelObj().get(end);
				        p = getEndPoints(startLab, endLab);
				     	g2.setPaint(Color.blue);
						g2.draw(new Line2D.Double(p[0], p[1]));
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						
			}
	}

	private Point[] getEndPoints(Component c1, Component c2) {
		Point p1 = new Point(), p2 = new Point();
		Rectangle r1 = c1.getBounds(), r2 = c2.getBounds();		
		p1.x = r1.x +(int) r1.width/2;
		p1.y = r1.y + (int) (r1.height / 2);
		p2.x = r2.x+(int)r2.width/2;
		p2.y = r2.y + (int) (r2.height / 2);
		addMouseListener(mover);
		addMouseMotionListener(mover);
		return new Point[] { p1, p2 };
	}

	public class ComponentMover extends MouseInputAdapter {
		Point offsetP = new Point();
		boolean dragging;
       
		public void mousePressed(MouseEvent e) {
			Point p = e.getPoint();
			//System.out.println("Mouse Pressed:::: "+p);
			for (int i = 0; i < arrayObj.size(); i++) {
				Rectangle r = arrayObj.get(i).getBounds();
				
				if (r.contains(p)) {
					
					selectedLabel = arrayObj.get(i);
					offsetP.x = p.x - r.x;
					offsetP.y = p.y - r.y;
					dragging = true;
					break;
				}else{
					//System.out.println("Not Contains");
				}
			}
			
			
		}

		public void mouseReleased(MouseEvent e) {
			
			Iterator itera = arrayObj.iterator();
			Iterator iter =BugUIStorage.getNODES_MAPPING().entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry<String,String> ent= (Map.Entry<String, String>)iter.next();
				String name = ent.getKey();
				String[] value = ent.getValue().split("\\$");
			    for (int i = 0; i < arrayObj.size(); i++) {
			    	if(name.equalsIgnoreCase(arrayObj.get(i).getText())){
				         Rectangle r = arrayObj.get(i).getBounds();
				         arrayObj.get(i).setText(name);
				         str [i]= value[0];
				         r1[i] = r;
				       
			    	}
			     }
			    
			}
			
			dragging = false;
		}

		public void mouseDragged(MouseEvent e) {
			if (dragging) {
				
				Rectangle r = selectedLabel.getBounds();
				r.x = e.getX() - offsetP.x;
				r.y = e.getY() - offsetP.y;
				
				try{
				selectedLabel.setBounds(r.x, r.y, r.width, r.height);
				}catch(Exception e1){
					//e1.printStackTrace();
				}
				repaint();
			}
		}
		
	}
	
	
	   public DrawComponent getCurrentComponent(){
	       return this.currentComponent;
	   }
	   
	   public void createNewCurrentComponent(){
		   this.currentComponent = new DrawComponent();
	   }
	
	
	public void setBackgroundImage(String imgLocation){
	     this.imgLocation = imgLocation;
	   }
	   
	   /**
	    * Returns the value of imgLocation
	    * @return imgLocation
	    */
	   public String getBackgroundImage(){
	       return this.imgLocation;
	   }
	   
	   public MonitorToolbar getToolBar() {
			return toolBar;
		}
		public void setToolBar(MonitorToolbar toolBar) {
			this.toolBar = toolBar;
		}
	}
