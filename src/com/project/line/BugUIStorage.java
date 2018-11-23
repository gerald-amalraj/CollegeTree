package com.project.line;


import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BugUIStorage {
	
	private static HashMap<String,ImageIcon> TOPO_STATE_IMAGES;
	private static HashMap<String,ImageIcon> BACKGROUND_STATE_IMAGES;

	private static HashMap<String,String> NODES_MAPPING = new HashMap<String,String>();
	private static HashMap<String,String> LINKS_MAPPING = new HashMap<String,String>();
	private static HashMap<String, JLabel> labelObj      = new HashMap<String,JLabel>();
	private static URL codeBase =null;
	private static boolean hideDis = false;
    private static URL curntBackgrndImg = null;
	
	public static URL getCurntBackgrndImg() {
		return curntBackgrndImg;
	}

	public static void setCurntBackgrndImg(URL curntBackgrndImg) {
		BugUIStorage.curntBackgrndImg = curntBackgrndImg;
	}
	
	public static URL getCodeBase() {
		return codeBase;
	}

	public static void setCodeBase(URL codeBase) {
		BugUIStorage.codeBase = codeBase;
	}
	
	public static boolean isHideDis() {
		return hideDis;
	}

	public static void setHideDis(boolean hideDis) {
		BugUIStorage.hideDis = hideDis;
	}

	public static HashMap<String, ImageIcon> getBACKGROUND_STATE_IMAGES() {
		return BACKGROUND_STATE_IMAGES;
	}

	public static void setBACKGROUND_STATE_IMAGES(
			HashMap<String, ImageIcon> bACKGROUNDSTATEIMAGES) {
		BACKGROUND_STATE_IMAGES = bACKGROUNDSTATEIMAGES;
	}
	
	
	public static HashMap<String, JLabel> getLabelObj() {
		return labelObj;
	}

	public static void setLabelObj(String name, JLabel labObj) {
		labelObj.put(name, labObj) ;
	}

	public static HashMap<String, String> getNODES_MAPPING() {
		return NODES_MAPPING;
	}

	public static void setNODES_MAPPING(HashMap<String, String> nODESMAPPING) {
		NODES_MAPPING = nODESMAPPING;
	}
	public static HashMap<String, String> getLINKS_MAPPING() {
		return LINKS_MAPPING;
	}

	public static void setLINKS_MAPPING(String xmlId, String lINKSMAPPING) {
		//System.out.println(xmlId+"  ~~~~~~~~~~~~~~~~~~ "+lINKSMAPPING);
		LINKS_MAPPING.put(xmlId, lINKSMAPPING);
	}

	public static HashMap<String, ImageIcon> getTOPO_STATE_IMAGES() {
		return TOPO_STATE_IMAGES;
	}

	public static void setTOPO_STATE_IMAGES(HashMap<String, ImageIcon> tOPOSTATEIMAGES) {
		TOPO_STATE_IMAGES = tOPOSTATEIMAGES;
	}

}
