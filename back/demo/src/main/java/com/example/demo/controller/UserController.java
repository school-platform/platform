package com.example.demo.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.tooldao.AdministratorToolMapper;
import com.example.demo.dao.tooldao.OrgnizationToolMapper;
import com.example.demo.dao.tooldao.StudenttoolMapper;
import com.example.demo.services.AdministratorService;
import com.example.demo.services.OrgnizationService;
import com.example.demo.services.StudentsService;
import com.example.demo.tools.FileUtils;
import com.example.demo.tools.JsonMessage;

import net.sf.json.JSONObject;

@Controller
public class UserController {
	
	@Autowired
	AdministratorService administratorService;
	@Autowired
	StudenttoolMapper studentToolMapper;
	@Autowired
	OrgnizationToolMapper orgnizationToolMapper;
	@Autowired
	AdministratorToolMapper administratorToolMapper;

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
		boolean flag = false;
		if("student".equals(type))
			if(studentToolMapper.loginCheck(userid, pass)>0) flag = true;
		if("manager".equals(type))
			if(administratorToolMapper.loginCheck(userid, pass)>0) flag = true;
		if("orgnization".equals(type))
			if(orgnizationToolMapper.loginCheck(userid, pass)>0) flag = true;
		if(flag) {
			HttpSession session = request.getSession(); 
			session.setAttribute("user_id", userid);
			Cookie cookie = new Cookie("user_id", userid);
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
	
	@RequestMapping(value = "/getAllCollege" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getAllCollege() {
		try {
			return JsonMessage.success("学院信息查询成功", administratorService.getAllCollege());
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/getMajorByCollege" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getMajorByCollege(@RequestParam("col_id")String col_id) {
		try {
			return JsonMessage.success("专业信息查询成功", administratorService.getMajorByCollege(col_id));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@Autowired
	StudentsService studentService;
	//获取最新活动
	@RequestMapping(value = "/newAct" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject newAct(@RequestParam("now")int now,@RequestParam("num")int num) {//@RequestParam("page")int page,@RequestParam("num")int num
		try {
			return JsonMessage.success("最新活动获取成功", studentService.getNewAct(now, num));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	

	//获取热门活动
	@RequestMapping(value = "/hotAct" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject hotAct(@RequestParam("now")int now,@RequestParam("num")int num) {
		try {
			return JsonMessage.success("热门活动获取成功", studentService.getHotAct(now, num));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@Autowired
	OrgnizationService orgnizationService;
	//获取评论列表
	@RequestMapping(value = "/getComments" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getComments(@RequestParam("act_id")String act_id,@RequestParam("now")int now,@RequestParam("num")int num) {
		try {
			return JsonMessage.success("评论获取成功", orgnizationService.getAllComments(act_id, now, num));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/getRewardList" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getRewardList(@RequestParam("act_id")int act_id,@RequestParam("now") int now,@RequestParam("num")int num) {
		try {
			return JsonMessage.success("公示列表获取成功", orgnizationService.getReWardList(act_id,now,num));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/getActInfo" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getActInfo(@RequestParam("act_id")String act_id) {
		try {
			return JsonMessage.success("活动信息获取成功", administratorService.getActInfo(act_id));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
}
