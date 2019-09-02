package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.services.OrgnizationService;
import com.example.demo.tools.JsonMessage;
import com.example.demo.tools.TimeExchange;

import net.sf.json.JSONObject;

@Controller
public class OrgnizationController {
	@Autowired
	OrgnizationService orgnizationService;
	
	@RequestMapping(value = "/organ/getinfo" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getOrgInfos(@RequestParam("org_id")String org_id) {
		try {
			return JsonMessage.success("社团信息查询成功", orgnizationService.getOrginfos(org_id));
		} catch (Exception e) {
			return JsonMessage.error("社团查询信息查询失败");
		}
	}
	
	@RequestMapping(value = "/organ/alterphone" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject alterPhone(@RequestParam("org_id")String org_id, @RequestParam("phone")String phone) {
		try {
			return JsonMessage.success("社团联系方式修改成功！", orgnizationService.alterphone(org_id, phone));
		}catch (Exception e) {
			return JsonMessage.error("社团联系方式修改成功!");
		}
	}
	
	@RequestMapping(value = "/organ/getVipList" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getVipList(@RequestParam("org_id")String org_id,@RequestParam("page")int page,@RequestParam("num")int num) {
		try {
			return JsonMessage.success("会员信息查询成功", orgnizationService.getVipList(org_id, page, num));
		}catch (Exception e) {
			return JsonMessage.error("社团信息查询失败");
		}
	}
	
	@RequestMapping(value = "organ/addVip" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addVip(@RequestParam("org_id")String org_id,@RequestParam("stu_id")String stu_id) {
		try {
			return JsonMessage.success("会员添加成功", orgnizationService.addVip(org_id, stu_id));
		}catch (Exception e) {
			return JsonMessage.error("会员添加失败");
		}
	}
	
	@RequestMapping(value = "organ/deleteVip" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteVip(@RequestParam("org_id")String org_id,@RequestParam("stu_id")String stu_id) {
		try {
			return JsonMessage.success("会员删除成功", orgnizationService.deleteVip(org_id, stu_id));
		}catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "organ/getActList" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getActList(@RequestParam("org_id")String org_id,@RequestParam("page")int page,@RequestParam("num")int num) {
		try {
			return JsonMessage.success("活动列表查询成功", orgnizationService.getActList(org_id, page, num));
		}catch (Exception e) {
			return JsonMessage.error("活动列表查询失败");
		}
	}
	
	@RequestMapping(value = "organ/getOrgMsg" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getOrgMSg(@RequestParam("org_id")String org_id) {
		try {
			return JsonMessage.success("社团消息查询成功", orgnizationService.getOrgMsg(org_id));
		}catch (Exception e) {
			return JsonMessage.error("社团信息查询失败"+e.getMessage());
		}
	}
	
	@RequestMapping(value = "organ/delOrgMsg" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteOrgMsg(@RequestParam("org_id")String org_id,@RequestParam("msg_id")String msg_id) {
		try {
			return JsonMessage.success("社团信息查询成功", orgnizationService.deleteMsgByOrgMsg(org_id, msg_id));
		}catch (Exception e) {
			return JsonMessage.error("社团信息删除失败");
		}
	}
	
	@RequestMapping(value = "organ/readMsg" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject readMsg(@RequestParam("org_id")String org_id,@RequestParam("msg_id")String msg_id) {
		try {
			return JsonMessage.success("消息状态修改完成", orgnizationService.readMsg(org_id, msg_id));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "organ/getRegisterList" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getRegisterList(@RequestParam("act_id")String act_id,@RequestParam("page")int page,@RequestParam("num")int num) {
		try {
			return JsonMessage.success("报名列表获取成功", orgnizationService.getRegisterList(act_id, page, num));
		} catch (Exception e) {
			return JsonMessage.error("报名列表获取失败"+e.getMessage());
		}
	}
	
	@RequestMapping(value = "organ/getSignList" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getSignList(@RequestParam("act_id")String act_id,@RequestParam("page")int page,@RequestParam("num")int num) {
		try {
			return JsonMessage.success("签到信息查询成功", orgnizationService.getSignList(act_id, page, num));
		} catch (Exception e) {
			return JsonMessage.error("签到信息查询失败");
		}
	}
	
	@RequestMapping(value = "organ/registerManage" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject registerManager(@RequestParam("act_id")String act_id,@RequestParam("stu_id")String stu_id,@RequestParam("ispass")boolean ispass,@RequestParam("message")String message) {
		try {
			return JsonMessage.success("活动报名管理成功", orgnizationService.registerManage(act_id, stu_id, ispass, message));
		} catch (Exception e) {
			return JsonMessage.error("活动报名管理失败");
		}
	}
	
	@RequestMapping(value = "organ/signManage" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject signManage(@RequestParam("act_id")String act_id,@RequestParam("stu_id")String stu_id,@RequestParam("ispass")boolean ispass,@RequestParam("message")String message) {
		try {
			return JsonMessage.success("活动签到操作成功", orgnizationService.signManage(act_id, stu_id, ispass, message));
		} catch (Exception e) {
			return JsonMessage.error("活动签到操作失败");
		}
	}
	
	@RequestMapping(value = "organ/getCommentList" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getCommentList(@RequestParam("act_id")String act_id,@RequestParam("page")int page,@RequestParam("num")int num) {
		try {
			return JsonMessage.success("活动评论获取成功", orgnizationService.getCommenList(act_id, page, num));
		} catch (Exception e) {
			return JsonMessage.error("活动评论获取失败");
		}
	}
	
	@RequestMapping(value = "organ/deleteComment" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deletComment(@RequestParam("com_id")String com_id) {
		try {
			return JsonMessage.success("活动评论删除成功", orgnizationService.deleteComment(com_id));
		} catch (Exception e) {
			return JsonMessage.error("活动评论删除失败");
		}
	}
	
	@RequestMapping(value = "organ/getScoreList" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getScoreList(@RequestParam("act_id")String act_id) {
		try {
			return JsonMessage.success("学分列表获取成功", orgnizationService.getScoreList(act_id));
		} catch (Exception e) {
			return JsonMessage.error("学分列表获取失败");
		}
	}
	
	@RequestMapping(value = "organ/grade" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject grade(@RequestParam("act_id")String act_id,@RequestParam("stu_id")String stu_id,@RequestParam("score_id")String score_id) {
		try {
			return JsonMessage.success("评分操作成功", orgnizationService.grade(score_id, stu_id, act_id));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "organ/publishActivity" , method = RequestMethod.POST)
	@ResponseBody
	public JSONObject publishActivity(@RequestParam("data")JSONObject data) {
		try {
			return JsonMessage.success("活动发布成功，请等待审核", orgnizationService.publishActivity(data));
		} catch (Exception e) {
			return JsonMessage.error(e.getMessage());
		}
	}
}
