package com.project.line;

import java.util.*;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		LinkedHashMap<String,String> links = new LinkedHashMap<String,String>();
		
		links.put("1", "A is connnected to K");
		links.put("2", "B is connnected to Q");
		links.put("3", "C is connnected to R");
		links.put("4", "D is connnected to S");
		links.put("5", "E is connnected to T");
		
		
		Iterator iter = links.entrySet().iterator();
		
		while(iter.hasNext()){
			
			Map.Entry<String, String> ent = (Map.Entry<String, String>)iter.next();
			
			
			
			String value = ent.getValue();
			
			String start = value.substring(0, value.indexOf("is")).trim();
			String end   = value.substring(value.indexOf("to")+2, value.length()).trim();
			System.out.println(start+" %%%%%%%%%%%% "+end);
		}
		
		
		
	}

}
