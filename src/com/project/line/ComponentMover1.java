package com.project.line;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Map;

import javax.swing.event.MouseInputAdapter;

public class ComponentMover1 extends DrawLine {
		Point offsetP = new Point();
		boolean dragging;
	     //DrawLine drawline = new DrawLine();
	     
		public void mousePressed(MouseEvent e) {
			System.out.println("wefewfwefefew");
			Point p = e.getPoint();
			
			for (int i = 0; i < arrayObj.size(); i++) {
				Rectangle r = arrayObj.get(i).getBounds();
				
				if (r.contains(p)) {
					selectedLabel =arrayObj.get(i);
					offsetP.x = p.x - r.x;
					offsetP.y = p.y - r.y;
					dragging = true;
					break;
				}
			}
		}

		public void mouseReleased(MouseEvent e) {
			Iterator itera =arrayObj.iterator();
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
					//System.out.println(" r.x:::: "+r.x+ " r.y::::::: "+r.y+" r.width:::: "+r.width+" r.height "+r.height );
					selectedLabel.setBounds(r.x, r.y, r.width, r.height);
				}catch(Exception e1){
					e1.printStackTrace();
				}
				repaint();
			}
		}
		
	}