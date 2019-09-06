package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.StudentsService;
import com.example.demo.tools.JsonMessage;

import net.sf.json.JSONObject;

@RestController
public class StudentController {
	@Autowired
	StudentsService ss;
	
	//学生请求详细信息
	@RequestMapping(value = "/student/getinfo" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getStudentInfo(@RequestParam("stu_id") String stu_id){
		try {
			Object obj = ss.getStudentById(stu_id);
			System.out.println(obj.toString());
			return JsonMessage.success("查询学生成功", obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(JsonMessage.error(e.getMessage()));
			return JsonMessage.error(e.getMessage());
		}
	}
	
	//学生修改手机号
	@RequestMapping(value = "student/alterphone" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject alterphone (@RequestParam("stu_id")String stu_id,@RequestParam("phone")String phone) {
		try {
			ss.alterPhone(stu_id, phone);
			return JsonMessage.success("更新成功", null);
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	//学生请求社团信息
	@RequestMapping(value = "student/getorgs" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getorgs(@RequestParam("stu_id")String stu_id) {
		try {
			return JsonMessage.success("查询成功",ss.getOrgInfo(stu_id));
		}catch(Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	//学生请求消息
	@RequestMapping(value = "student/getnews" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getNews(@RequestParam("stu_id")String stu_id) {
		try {
			return JsonMessage.success("查询成功", ss.getNews(stu_id));
		}catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	//学生删除消息
	@RequestMapping(value = "student/deletenews" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteNews(@RequestParam("stu_id")String stu_id,@RequestParam("msg_id")String msg_id) {
		try {
			System.out.println(stu_id+"||"+msg_id);
			return JsonMessage.success("消息删除成功", ss.deleteMessage(stu_id, msg_id));
		}catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	//学生阅读消息
	@RequestMapping(value = "student/readnews" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject readNews(@RequestParam("stu_id")String stu_id,@RequestParam("msg_id")String msg_id) {
		try {
			System.out.println(stu_id+"||"+msg_id);
			return JsonMessage.success("已成功设置为已读！", ss.readNews(stu_id, msg_id));
		}catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	//学生请求自己的学分信息
	@RequestMapping(value = "student/getScoreInfo" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getScoreInfo(@RequestParam("stu_id")String stu_id) {
		try {
			return JsonMessage.success("学分信息查询成功", ss.getStuScoreInfos(stu_id));
		}catch (Exception e) {
			return JsonMessage.error(e.getMessage());
			// TODO: handle exception
		}
	}
}
