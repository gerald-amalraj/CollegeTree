package com.project.line;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class Configuration extends JFrame implements ActionListener {
	JList lst;
	JButton b1;
	String[] ch = new String[5];
	int i=0;
	 int tabb=1;
	JFrame frame= new JFrame();
	Container con;
	//File dir = new File("./exam");
	Iterator iter = BugUIStorage.getBACKGROUND_STATE_IMAGES().entrySet().iterator();
	
	public Configuration() {
		while(iter.hasNext()){
			Map.Entry<String,ImageIcon> image = (Map.Entry<String, ImageIcon>)iter.next();
			ch[i]=image.getKey();
			i++;
		
		}
		
		//ch = dir.list();
		lst = new JList(ch);
		b1 = new JButton("Apply");
		JPanel jp = new JPanel(new BorderLayout());
	
		JPanel p = new JPanel();
		p.add(lst);
		p.add(b1);
		 
		JTabbedPane t1;
		t1 = new JTabbedPane();
		t1.addTab("GENERAL", new JPanel());
		t1.addTab("FORMAT", new JPanel());
		t1.addTab("SCREEN", p);
		t1.addTab("ADVANCED", new JPanel());
		b1.addActionListener(this);
		jp.add(t1);
		con =getContentPane();
		con.add(jp);
		
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we)
            {
            	 
             	MonitorToolbar toolbar= new MonitorToolbar();
            	toolbar.varAssign();
            }
   });
		
       
		this.setSize(400, 400);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		try {
			
			String petName = (String) lst.getSelectedValue();
			BugUIStorage.setCurntBackgrndImg(new URL(BugUIStorage.getCodeBase()+"backgrounds/"+petName+".png"));
			BugUIStorage.setHideDis(true);
			DrawLine.getPanel().repaint();
		} catch (Exception ea) {
			ea.printStackTrace();
		}
	}
	
	

}
