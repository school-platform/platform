package com.example.demo.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.tools.FileUtils;
import com.example.demo.tools.JsonMessage;

import net.sf.json.JSONObject;

@Controller
public class UserController {

	@Value("${img.path}")
	private String path;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject Login(HttpServletResponse response, HttpServletRequest request) {
		System.out.println("登陆验证");
		String userid = request.getParameter("userid");// 普通表单数据
		String pass = request.getParameter("password");// 普通表单数据
		String type=request.getParameter("type");
		//验证用户名与密码
		if(true) {
			HttpSession session = request.getSession();
			session.setAttribute("userid", userid);
			Cookie cookie = new Cookie("userid", userid);
			response.addCookie(cookie);
			Map<String, String> data = new HashMap<String, String>();
			data.put("userid", userid);
			data.put("username", "伍方健");
			data.put("type", type);
			return JsonMessage.success("登陆成功", data);
		}
		return JsonMessage.error("登陆失败");
		
		
	}
	
	@RequestMapping(value = "/islogin", method = RequestMethod.POST)
	@ResponseBody
	public boolean isLogin(HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user=(String) session.getAttribute("username");
		if(user!=null)
			System.out.println(user);
		return user!=null;
	}

	@RequestMapping(value = "/getImg", method = RequestMethod.POST)
	@ResponseBody
	public void getImg(HttpServletResponse response, HttpServletRequest request) {
		String filename = request.getParameter("filename");// 普通表单数据
		File file = new File(path+filename);
		FileUtils.responseTo(file, response);
	}

}
