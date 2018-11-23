package com.project.line;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UseOfColl {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		HashMap<String,String> firstMap = new HashMap<String,String>();
		
		firstMap.put("Murugan", "user");
		firstMap.put("Vijay", "user");
		firstMap.put("ActiveX", "plugin");
		firstMap.put("LoginPage", "Bug");
		firstMap.put("Murugan", "user1");
		
		HashMap<String,HashMap<String,String>> secondMap = new HashMap<String,HashMap<String,String>>();
		
		secondMap.put("elments", firstMap);
		
		HashMap<String,HashMap<String,HashMap<String,String>>> thrdMap = new HashMap<String,HashMap<String,HashMap<String,String>>>();
		
		thrdMap.put("table", secondMap);
		
		System.out.println("================= "+thrdMap);
		
		Iterator firstIter =  thrdMap.entrySet().iterator(); 
		while(firstIter.hasNext()){
			Map.Entry<String, HashMap> firstEnt = (Map.Entry<String, HashMap>)firstIter.next();
			String firstKey = firstEnt.getKey();
			HashMap firstValue = firstEnt.getValue();
			
			System.out.println(firstKey+" @@@@@@@@@@@@@@ "+firstValue);
			
			Iterator secondIter = firstValue.entrySet().iterator();
			
			while(secondIter.hasNext()){
				Map.Entry<String, HashMap> secondEnt =(Map.Entry<String, HashMap>)secondIter.next();
				String secondKey = secondEnt.getKey();
				HashMap secondValue = secondEnt.getValue();
				
				System.out.println(secondKey+" $$$$$$$$$$$$$ "+secondValue);
				
				Iterator thrdIter = secondValue.entrySet().iterator();
				while(thrdIter.hasNext()){
					Map.Entry<String,String> thrdEnt = (Map.Entry<String,String>)thrdIter.next();
					String thrdKey = thrdEnt.getKey();
					String thrdValue = thrdEnt.getValue();
					
					System.out.println(thrdKey+" &&&&&&&&&&&&&& "+thrdValue);
				}
			}
			
		}
	}

}
