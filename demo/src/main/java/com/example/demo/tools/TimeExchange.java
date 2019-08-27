package com.example.demo.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class TimeExchange {
	public static ArrayList<Map<String,Object>> changeTimeDate(ArrayList<Map<String,Object>> list,ArrayList<String> names){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日");
		for(Map<String,Object> map:list) {
			for(String s:names) {
				if(!map.containsKey(s)) continue;
				String date = sdf.format((Date)map.get(s));
				map.put(s, date);
			}
		}
		return list;
	}
}
