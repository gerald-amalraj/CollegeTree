package com.project.line;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import noNamespace.NetworkDocument;

import org.apache.xmlbeans.XmlObject;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Parser {
		
	XmlObject xmlObj = null;
	Node allDocNodes = null;
		
	public void parserStarts(XmlObject xmlObject){
		try {
			xmlObj = xmlObject;
			
			if (xmlObj instanceof noNamespace.NetworkDocument) {
				NetworkDocument netDoc = (noNamespace.NetworkDocument) xmlObj;
				Node firstChild = netDoc.getDomNode().getFirstChild();
				for(int i=0;i<firstChild.getChildNodes().getLength();i++){
					String children = firstChild.getChildNodes().item(i).getNodeName();
					if(!children.equalsIgnoreCase("#text") && children.trim().equalsIgnoreCase("containers")){
						allDocNodes = firstChild.getChildNodes().item(i);
						break;	
					}
				}
			} 
		
		if(allDocNodes!=null){
			for(int i=0;i<allDocNodes.getChildNodes().getLength();i++){
				String execName = allDocNodes.getChildNodes().item(i).getNodeName();
				Node containerNode = allDocNodes.getChildNodes().item(i);
				if(!execName.trim().equalsIgnoreCase("#text"))
				
				if(execName.trim().equalsIgnoreCase("container")){
					processEachContainer(containerNode);
					
					if(containerNode.getChildNodes()!=null && containerNode.getChildNodes().getLength()>0){
						Node childNodes = null;
	                	Node childLinks = null;
		                	for (int j=0; j<containerNode.getChildNodes().getLength(); j++){
		                		Node processChildNode = containerNode.getChildNodes().item(j);
		                		if (processChildNode.getNodeName().trim().equals("nodes")){
		                			childNodes = processChildNode;
		                		}else if (processChildNode.getNodeName().trim().equals("links")){
		                			childLinks = processChildNode;
		                		}
		                	}
		                	
		                	if (childNodes!=null && childNodes.getChildNodes().getLength()>0){
	                			this.nodeToMapSymbolCompo(childNodes);
		                	}
	                		if (childLinks!=null){
	                			this.linkToMapLinker(childLinks);
	                		}
					}
				}
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void linkToMapLinker(Node chldLinks){
		String ip1=null,ip2=null;
		int linkLength = chldLinks.getChildNodes().getLength();
		
		for(int i=0; i<linkLength;i++){
			Node processLink = chldLinks.getChildNodes().item(i);
			
			NamedNodeMap linkProcs =processLink.getAttributes();
			if (!(processLink.getNodeName().trim().equals("#text"))){
			String xml = linkProcs.getNamedItem("xml_id").getNodeValue().trim();
			String name = linkProcs.getNamedItem("name").getNodeValue().trim();
			//System.out.println("xml=== "+xml+" name... "+name);  //-------------------------
			BugUIStorage.setLINKS_MAPPING(xml, name);
			
			if(processLink.getChildNodes()!= null && processLink.getChildNodes().getLength()>0){
				for(int j=0; j<processLink.getChildNodes().getLength();j++){
					Node prcsLinkNodes = processLink.getChildNodes().item(j);
					if(!prcsLinkNodes.getNodeName().equalsIgnoreCase("#text")){
						
						if(prcsLinkNodes.getNodeName().equalsIgnoreCase("start")){
							
							NamedNodeMap startTag = prcsLinkNodes.getAttributes();
							String xmlId = startTag.getNamedItem("xml_id").getNodeValue().trim();
							 ip1 = startTag.getNamedItem("ip").getNodeValue().trim();
							
							//System.out.println("xmld.^^^ "+xmlId+" ip&&& "+ip1);
							
						}else if(prcsLinkNodes.getNodeName().equalsIgnoreCase("end")){
							
							NamedNodeMap endTag = prcsLinkNodes.getAttributes();
							String xmlId = endTag.getNamedItem("xml_id").getNodeValue().trim();
							ip2 = endTag.getNamedItem("ip").getNodeValue().trim();
						}
					}
					
				}
			}
		}
		}
		
	}
	
	
	public void nodeToMapSymbolCompo(Node chldNodes){
		int nodesLength = chldNodes.getChildNodes().getLength();
		HashMap<String,String> nodeMapping = new HashMap<String,String>();
		for(int i=0; i<nodesLength;i++){
			Node processNode = chldNodes.getChildNodes().item(i);
			NamedNodeMap  nodeProcs =processNode.getAttributes();
			if (!(processNode.getNodeName().trim().equals("#text"))){
			String xml  = nodeProcs.getNamedItem("xml_id").getNodeValue().trim();
			String id   = nodeProcs.getNamedItem("id").getNodeValue().trim();
			String name = nodeProcs.getNamedItem("name").getNodeValue().trim();
			String type = nodeProcs.getNamedItem("type").getNodeValue().trim();
			String x = nodeProcs.getNamedItem("x").getNodeValue().trim();
			String y =nodeProcs.getNamedItem("y").getNodeValue().trim();
			nodeMapping.put(name, type+"$"+x+"$"+y);
			}
		}
		BugUIStorage.setNODES_MAPPING(nodeMapping);
	}
	
	public void processEachContainer(Node contanerName){
		NamedNodeMap contaner =  contanerName.getAttributes();
		String xml = contaner.getNamedItem("xml_id").getNodeValue().trim();
		String id = contaner.getNamedItem("id").getNodeValue().trim();
		String name = contaner.getNamedItem("name").getNodeValue().trim();
		String type = contaner.getNamedItem("type").getNodeValue().trim();
		String x = contaner.getNamedItem("x").getNodeValue().trim();
		String y = contaner.getNamedItem("y").getNodeValue().trim();
	}

	
}
