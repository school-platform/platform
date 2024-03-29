package com.example.demo.tools;
import java.util.HashMap;

import net.sf.json.JSONObject;

public class JsonMessage {
	public static HashMap<String, Object> success = new HashMap<String, Object>();
	public static HashMap<String, Object> error = new HashMap<String, Object>();

	public static JSONObject success(String msg,Object data) {
		success.clear();
		success.put("state", 200);
		success.put("msg", msg);
		success.put("data", data);
		return JSONObject.fromObject(success);
	}

	public static JSONObject error(String msg) {
		error.clear();
		error.put("state", "400");
		error.put("msg", msg);
		return JSONObject.fromObject(error);
	}
	
	public static void main(String[] args) {
		String[] s= {"1","2"};
		System.out.println(JsonMessage.success("登陆成功", s));
	}

}
