package com.project.line;


import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import org.apache.xmlbeans.XmlObject;

public class ServerLinkMaker{
    
    public static URL codePosition;
    
    public ServerLinkMaker() {
        
    }
    public XmlObject callMethod() throws Exception{
    	
    	List<Object> requestList = new ArrayList<Object>();
        requestList.add(0, "3");
        URLConnection con = getServletConnection("AppletCommunicationServlet");
        OutputStream outstream = con.getOutputStream();
        
        ObjectOutputStream oos = new ObjectOutputStream(outstream);
        oos.writeObject(requestList);
        oos.flush();
        oos.close();
        InputStream instr = con.getInputStream();
        
        ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
        System.out.println("inputFromServlet:::: "+inputFromServlet);
        XmlObject xmlObj   = (XmlObject)inputFromServlet.readObject();
        inputFromServlet.close();
        instr.close();
    	
    	
    	return xmlObj;
    }
    
    public XmlObject readFromXml() throws Exception {
    	
            List<Object> requestList = new ArrayList<Object>();
            requestList.add(0, "1");
            URLConnection con = getServletConnection("AppletCommunicationServlet");
            OutputStream outstream = con.getOutputStream();
            
            ObjectOutputStream oos = new ObjectOutputStream(outstream);
            oos.writeObject(requestList);
            oos.flush();
            oos.close();
            
            long strt = System.currentTimeMillis(); 
            InputStream instr = con.getInputStream();
            
            ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
            System.out.println("inputFromServlet:::: "+inputFromServlet);
            XmlObject xmlObj   = (XmlObject)inputFromServlet.readObject();
           // System.out.println("xmlohject"+xmlObj);
            long end = System.currentTimeMillis();
            inputFromServlet.close();
            instr.close();
            System.out.println("read Time (rxml) "+(end-strt)+" ms");
            return xmlObj;
    }
   public  void writetoXml(String xmlFileName,Rectangle[] r,ArrayList<JLabel> arrayObj,String[] str){
    	try{
    		List<Object> requestList = new ArrayList<Object>();
            requestList.add(0, "2");
            requestList.add(1,xmlFileName);
            requestList.add(2,r);
            requestList.add(3,arrayObj);
            requestList.add(4,str);
            URLConnection con = getServletConnection("AppletCommunicationServlet");
            OutputStream outstream = con.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outstream);
            oos.writeObject(requestList);
            oos.flush();
            oos.close();
            
           InputStream instr = con.getInputStream();
            
            ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
            System.out.println("inputFromServlet:::: "+inputFromServlet);
            inputFromServlet.close();
            instr.close();
    	}
    	catch(Exception ee){
    		
    	}
    }
    
    public static void setCodePosition(URL codePosition) {
        ServerLinkMaker.codePosition = codePosition;
    }

    
    private URLConnection getServletConnection(String servlet) throws MalformedURLException, IOException{
        //System.out.println(ServerLinkMaker.codePosition+" $$$$$$$$$$$$$$$$$ "+servlet);
        BugUIStorage.setCodeBase(ServerLinkMaker.codePosition);
    	URL urlServlet = new URL(ServerLinkMaker.codePosition, servlet);
        URLConnection con = urlServlet.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setRequestProperty("Content-Type","text/html");
        return con;
    }
}
