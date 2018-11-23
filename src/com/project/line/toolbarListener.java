package com.project.line;


public class toolbarListener extends DrawLine{
	 public void toolclick(){

			try{
				//ElementsCreation();
			String xmlChk = new String("C:/opt/Bug_Tracker/BugTracker.xml");
			//SaveXML savexml = new SaveXML();
			//savexml.buildXmlFile(xmlChk, r1, arrayObj,str);
			ServerLinkMaker server = new ServerLinkMaker();
			server.writetoXml(xmlChk, r1, arrayObj,str);
			}
			catch(Exception e1){
				System.out.println(e1);
			}
			
		}
     
	
		
	
}