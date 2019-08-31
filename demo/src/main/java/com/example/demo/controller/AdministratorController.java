package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.services.AdministratorService;
import com.example.demo.tools.JsonMessage;

import net.sf.json.JSONObject;

@Controller
public class AdministratorController {
	
	@Autowired
	AdministratorService administratorService;
	
	@RequestMapping(value = "admin/getAllCollege" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getAllCollege() {
		try {
			return JsonMessage.success("学院信息查询成功", administratorService.getAllCollege());
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "admin/getMajorByCollege" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject RequestMethod(@RequestParam("col_id")String col_id) {
		try {
			return JsonMessage.success("专业信息查询成功", administratorService.getMajorByCollege(col_id));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "admin/getAllStudent" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getAllStudent(@RequestParam("page")int page,@RequestParam("num")int num) {
		try {
			return JsonMessage.success("学生列表查询成功", administratorService.getAllStudent(page, num));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "admin/getAllOrgnization" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getAllOrgnization(@RequestParam("page")int page,@RequestParam("num")int num) {
		try {
			return JsonMessage.success("社团列表查询成功", administratorService.getAllOrgnization(page, num));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "admin/addStudent" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addStudent(@RequestParam("data")JSONObject data) {
		try {
			return JsonMessage.success("学生添加成功",administratorService.addStudent(data));
		} catch (Exception e) {
			return JsonMessage.error("学生添加失败");
		}
	}
	
	@RequestMapping(value = "admin/addOrgnization" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addOrgnization(@RequestParam("data")JSONObject data) {
		try {
			return JsonMessage.success("社团添加成功", administratorService.addOrgnization(data));
		} catch (Exception e) {
			return JsonMessage.error("添加社团失败");
		}
	}
	
	@RequestMapping(value = "admin/modifyStudent" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject modifyStudent(@RequestParam("data")JSONObject data) {
		try {
			return JsonMessage.success("学生修改成功", administratorService.modifyStudent(data));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "admin/getAllExamine" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getAllExamine(@RequestParam("page")int page,@RequestParam("num")int num) {
		try {
			return JsonMessage.success("社团列表查询成功", administratorService.getAllExmaine(page, num));
		} catch (Exception e) {
			return JsonMessage.error("审核列表查询成功");
		}
	}
	
	@RequestMapping(value = "admin/exmaine" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject examine(@RequestParam("id")int id,@RequestParam("ispass")boolean ispass,@RequestParam("result")String result) {
		try {
			return JsonMessage.success("审核成功", administratorService.examine(id, ispass, result));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "admin/getActInfo" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getActInfo(@RequestParam("act_id")String act_id) {
		try {
			return JsonMessage.success("活动信息获取成功", administratorService.getActInfo(act_id));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "admin/publishMessage" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject publishMessage(@RequestParam("isstudent")boolean isstudent,@RequestParam("isorgan")boolean isorgan,@RequestParam("text")String text) {
		try {
			return JsonMessage.success("消息发布成功", administratorService.publishMessage(isstudent, isorgan, text));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
}
