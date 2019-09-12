package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.tooldao.StudenttoolMapper;
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
	
	//修改密码
	@RequestMapping(value = "student/upPass" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject upPassword( @RequestParam("old_pass")String old_pass,@RequestParam("stu_id")String stu_id,@RequestParam("password")String password) {
		try {
			return JsonMessage.success("学生密码修改成功", ss.upPass(old_pass,password, stu_id));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	//获取参加信息
	@RequestMapping(value = "student/getPartInfo" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getPartInfo(@RequestParam("act_id")String act_id,@RequestParam("stu_id")String stu_id) {
		try {
			return JsonMessage.success("参与信息获取成功", ss.getPartInfo(act_id, stu_id));
		} catch (Exception e) {
			if("notfound".equals(e.getMessage())) {
				return JsonMessage.success("未参与活动", null);
			}else {
				return JsonMessage.error("参与信息获取失败");
			}
		}
	}
	
	//个人报名
	@RequestMapping(value = "student/postRegister" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject postRigster(@RequestParam("stu_id")String stu_id,@RequestParam("act_id")String act_id) {
		try {
			return JsonMessage.success("报名操作成功", ss.addParticipant(act_id, stu_id));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	//创建团队
	@RequestMapping(value = "student/createTeam" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject createTeam(@RequestParam("stu_id")String stu_id,@RequestParam("act_id")String act_id) {
		try {
			return JsonMessage.success("团队建立成功", ss.addParticipant(act_id, stu_id));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	
	//同意入队
	@RequestMapping(value = "student/confirm" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject confirm(@RequestParam("stu_id")String stu_id,@RequestParam("mem_id")	String mem_id,@RequestParam("act_id")String act_id) {
		try {
			return JsonMessage.success("操作成功", ss.confirmTm(stu_id, mem_id, act_id));
		} catch (Exception e) {
			return JsonMessage.error("操作失败"+e.getMessage());
		}
	}
	
	//申请入团
	@RequestMapping(value = "student/postTeam" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject postTeam(@RequestParam("stu_id")String stu_id,@RequestParam("leader_id")String leader_id,@RequestParam("act_id")String act_id) {
		try {
			return JsonMessage.success("成功入团", ss.joinTeam(leader_id, stu_id,act_id));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	//拒绝入团
	@RequestMapping(value = "student/cancel" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject cancel(@RequestParam("stu_id")String stu_id,@RequestParam("mem_id")String mem_id,@RequestParam("act_id")String act_id) {
		try {
			return JsonMessage.success("拒绝成功", ss.cancel(stu_id, mem_id, act_id));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	//获取学生的活动
	@RequestMapping(value = "student/getMyAct" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getMyAct(@RequestParam("stu_id")String stu_id,@RequestParam("now")int now,@RequestParam("count")int count) {
		try {
			return JsonMessage.success("我的火哦的那个获取成功", ss.getMyAct(stu_id,now,count));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}

	
}
