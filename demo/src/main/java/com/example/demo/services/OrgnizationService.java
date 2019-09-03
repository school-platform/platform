package com.example.demo.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Act_examineMapper;
import com.example.demo.dao.Act_scoreMapper;
import com.example.demo.dao.ActinfosMapper;
import com.example.demo.dao.ActivityMapper;
import com.example.demo.dao.CommentsMapper;
import com.example.demo.dao.MessageMapper;
import com.example.demo.dao.Msg_stu_orgMapper;
import com.example.demo.dao.OrganizationMapper;
import com.example.demo.dao.tooldao.OrgnizationToolMapper;
import com.example.demo.dao.tooldao.StudenttoolMapper;
import com.example.demo.domain.Act_examine;
import com.example.demo.domain.Act_score;
import com.example.demo.domain.Actinfos;
import com.example.demo.domain.Activity;
import com.example.demo.domain.Message;
import com.example.demo.domain.Msg_stu_org;
import com.example.demo.tools.TimeExchange;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service
public class OrgnizationService {
	
	@Autowired
	StudenttoolMapper studenttoolMapper;
	//查询社团详细信息
	@Autowired
	OrgnizationToolMapper orgnizationToolMapper;
	public ArrayList<Map<String,Object>> getOrginfos(String org_id) throws Exception{
		ArrayList<Map<String,Object>> list = orgnizationToolMapper.getOrgInfo(org_id);
		//便利map调整date为字符串
		for(Map map:list) {
			Date date = (Date)map.get("foundtime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日");
			String strFoundTime = sdf.format(date);
			map.put("foundtime",strFoundTime);
		}
		return list;
	}
	
	//修改社团电话
	public int alterphone(String org_id,String phone) throws Exception{
		return orgnizationToolMapper.alterphone(org_id, phone);
	}
	
	//查询社团会员
	public ArrayList<Map<String,Object>> getVipList(String org_id,int page,int num) throws Exception{
		ArrayList<String> s = new ArrayList<String>();
		s.add("jointime");
		ArrayList<Map<String,Object>> list = orgnizationToolMapper.getVipList(org_id, (page-1)*num, num);
		TimeExchange.changeTimeDate(orgnizationToolMapper.getVipList(org_id, (page-1)*num, num), s);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count_stu", orgnizationToolMapper.getCountStu(orgnizationToolMapper.getIDbyOrgID(org_id)));
		list.add(map);
		return list;
	}
	
	//添加会员
	public int addVip(String org_id,String stu_id) throws Exception{
		int stuid = studenttoolMapper.getIDByStudentID(stu_id);
		int orgid = orgnizationToolMapper.getIDbyOrgID(org_id);
		Date jointime = new Date();
		if(orgnizationToolMapper.isVip(stuid).isEmpty())
			return orgnizationToolMapper.addVip(orgid, stuid, jointime);
		else throw new Exception("该同学已经是会员了！");
	}
	
	//删除会员
	public int deleteVip(String org_id,String stu_id) throws Exception{
		try {
			int stuid = studenttoolMapper.getIDByStudentID(stu_id);
			int orgid = orgnizationToolMapper.getIDbyOrgID(org_id);
			Map map = orgnizationToolMapper.isInOrg(orgid, stuid);
			if(map.isEmpty()) throw new Exception("学生已不在社团中");
			return orgnizationToolMapper.deleteVip(orgid, stuid);
		}catch (Exception e) {
			throw new Exception("会员删除失败");
		}
	}
	
	//获取活动列表
	public ArrayList<Map<String,Object>> getActList(String org_id,int page,int num) throws Exception{
		try {
			int snum = (page-1)*num;
			int orgid = orgnizationToolMapper.getIDbyOrgID(org_id);
			ArrayList<Map<String,Object>> list = orgnizationToolMapper.getActList(orgid, snum, num);
			ArrayList<String> names = new ArrayList<String>();
			names.add("posttime");
			names.add("extime");
			list = TimeExchange.changeTimeDate(list, names);
			//对于未审核的活动写extime为无
			for(Map<String,Object> map:list) {
				if(map.get("result").equals("未审核")) {
					map.put("posttime", " ");
				}
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("count_act", orgnizationToolMapper.getCountActivity(orgid));
			list.add(map);
			return list;
		} catch (Exception e) {
			throw new Exception("活动列表查询失败"+e.getMessage());
		}
	}
	
	//社团请求消息
	public ArrayList<Map<String,Object>> getOrgMsg(String org_id) throws Exception{
		try {
			System.out.println(0);
			//查询社团id
			int orgid = orgnizationToolMapper.getIDbyOrgID(org_id);
			System.out.println(1);
			//查询信息列表
			ArrayList<Map<String,Object>> list = orgnizationToolMapper.getOrgMsg(orgid);
			System.out.println(2);
			//转换时间类型
			ArrayList<String> names = new ArrayList<String>();
			names.add("time");
			System.out.println(3);
			TimeExchange.changeTimeDate(list, names);
			System.out.println(4);
			return list;
		}catch (Exception e) {
			throw new Exception("社团消息查询失败"+e.getMessage());
		}
	}
	
	//社团删除消息
	public int deleteMsgByOrgMsg(String org_id,String msg_id) throws Exception{
		try {
			String orgid = String.valueOf(orgnizationToolMapper.getIDbyOrgID(org_id));
			return orgnizationToolMapper.deleteMsgByOrgMsg(orgid, msg_id);
		}catch (Exception e) {
			throw new Exception("社团消息删除失败");
		}
	}
	
	//社团阅读消息
	public int readMsg(String org_id,String msg_id) throws Exception {
		try {
			int orgid = orgnizationToolMapper.getIDbyOrgID(org_id);
			int msgid = Integer.parseInt(msg_id);
			boolean state = !(orgnizationToolMapper.msgIsRead(msgid, orgid));
			return orgnizationToolMapper.readMsg(state, msgid, orgid);
		} catch (Exception e) {
			throw new Exception("消息状态修改失败");
		}
	}
	
	//获取活动详细信息
	public ArrayList<Map<String,Object>> getActDetail(String act_id) throws Exception{
		try {
			//先组装活动详细信息01
			int actid = Integer.parseInt(act_id);
			ArrayList<Map<String,Object>> list = orgnizationToolMapper.getActInfos(actid);
			//将时间元素转换
			ArrayList<String> names = new ArrayList<String>();
			names.add("starttime");
			names.add("endtime");
			TimeExchange.changeTimeDate(list, names);
			//查询活动学分信息
			ArrayList<Map<String,Object>> score_list = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> map:list) {
				score_list = orgnizationToolMapper.getActScores((int)map.get("id"));
				map.put("score",score_list);
			}
			//返回查询到的信息
			return list;
		} catch (Exception e) {
			throw new Exception("活动详细信息查询失败"+e.getMessage());
		}
	}
	
	//获取报名列表
	public ArrayList<Map<String,Object>> getRegisterList(String act_id,int page,int num) throws Exception{
		try {
			int snum = (page-1)*num;
			int actid = Integer.parseInt(act_id);
			//查询一级参与人员
			ArrayList<Map<String,Object>> list = orgnizationToolMapper.getFirstMembers(actid, snum, num);
			ArrayList<String> names = new ArrayList<String>();
			names.add("posttime");
			names.add("jointime");
			TimeExchange.changeTimeDate(list, names);
			//判断是否为组队互动
			boolean isteam = orgnizationToolMapper.isTeam(actid);
			//组装队员信息
			ArrayList<Map<String,Object>> list2;
			if(isteam) {
				for(Map<String,Object> map:list) {
					System.out.println("002");
					list2 = orgnizationToolMapper.getTeamMember((int)map.get("ap_id"));
					System.out.println("003");
					System.out.println(list2);
					System.out.println(names);
					if(!list2.isEmpty()) list2 = TimeExchange.changeTimeDate(list2, names);
					System.out.println("004");
					map.put("member", list2);
					System.out.println("001");
				}
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("count_par", orgnizationToolMapper.getCountParticipant(actid));
			list.add(map);
			//返回需要的信息
			return list;
		} catch (Exception e) {
			throw new Exception("报名信息查询失败"+e.getMessage());
		}
	}
	
	//获取签到列表
	public ArrayList<Map<String,Object>> getSignList(String act_id,int page,int num) throws Exception{
		try {
			int actid = Integer.parseInt(act_id);
			int snum = (page-1)*num;
			ArrayList<Map<String,Object>> list = orgnizationToolMapper.getSignList(actid,snum,num);
			ArrayList<String> names = new ArrayList<String>();
			names.add("jointime");
			names.add("checktime");
			names.add("posttime");
			list = TimeExchange.changeTimeDate(list, names);
			boolean isteam = orgnizationToolMapper.isTeam(actid);
			if(isteam) {
				ArrayList<Map<String,Object>> list2;
				for(Map<String,Object> map:list) {
					list2 = orgnizationToolMapper.getTeamMember((int)map.get("ap_id"));
					if(!list2.isEmpty()) list2 = TimeExchange.changeTimeDate(list2, names);
					map.put("member", list2);
				}
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("count_par", orgnizationToolMapper.getCountParticipant(actid));
			list.add(map);
			return list;
		} catch (Exception e) {
			throw new Exception("签到信息查询失败"+e.getMessage());
		}
	}
	
	@Autowired
	ActivityMapper activityMapper;
	@Autowired
	MessageMapper messageMapper;
	@Autowired
	Msg_stu_orgMapper msg_stu_orgMapper;
	//报名管理
	public int registerManage(String act_id,String stu_id,boolean ispass,String message) throws Exception{
		try {
			int actid = Integer.parseInt(act_id);
			int stuid = studenttoolMapper.getIDByStudentID(stu_id);
			if(ispass) {
				Date jointime = new Date();
				return orgnizationToolMapper.checkjoin(jointime, actid, stuid);
			}
			else {
				int org_id = activityMapper.getOrgid(actid);
				Message msg = new Message();
				String orgid = String.valueOf(org_id);
				int msg_id = messageMapper.getLastId()+1;
				msg.setId(msg_id);
				msg.setSenderId(orgid);
				msg.setText(message);
				msg.setTime(new Date());
				messageMapper.insert(msg);
				Msg_stu_org mso = new Msg_stu_org();
				mso.setMsgId(msg_id);
				mso.setStuId(stuid);
				msg_stu_orgMapper.insert(mso);
				return orgnizationToolMapper.deleteJoin(actid, stuid);
			}
		} catch (Exception e) {
			throw new Exception("报名管理操作失败！"+e.getMessage());
		}
	}
	
	//给同学签到
	public int signManage(String act_id,String stu_id,boolean ispass,String message) throws Exception{
		try {
			int actid = Integer.parseInt(act_id);
			int stuid = studenttoolMapper.getIDByStudentID(stu_id);
			if(ispass) {
				Date checktime = new Date();
				return orgnizationToolMapper.checkSign();
			}
			else {
				int org_id = activityMapper.getOrgid(actid);
				Message msg = new Message();
				String orgid = String.valueOf(org_id);
				int msg_id = messageMapper.getLastId()+1;
				msg.setId(msg_id);
				msg.setSenderId(orgid);
				msg.setText(message);
				msg.setTime(new Date());
				messageMapper.insert(msg);
				Msg_stu_org mso = new Msg_stu_org();
				mso.setMsgId(msg_id);
				mso.setStuId(stuid);
				msg_stu_orgMapper.insert(mso);
				return 1;
			}
		} catch (Exception e) {
			throw new Exception("报名操作失败！"+e.getMessage());
		}
	}
	
	//获取活动评论列表
	public ArrayList<Map<String,Object>> getCommenList(String act_id,int page ,int num) throws Exception{
		try {
			int actid = Integer.parseInt(act_id);
			int snum = (page-1)*num;
			ArrayList<Map<String,Object>> list = orgnizationToolMapper.getComList(actid,snum,num);
			//组装父评论内容
			for(Map<String,Object> map:list) {
				if(map.get("parent_id")!=null) {
					map.put("parentText", orgnizationToolMapper.getComText((int)map.get("parent_id")));
				}
			}
			//转换时间类
			ArrayList<String> names = new ArrayList<String>();
			names.add("time");
			TimeExchange.changeTimeDate(list, names);
			//将结果返回
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("count_com", orgnizationToolMapper.getConuntCommen(actid));
			list.add(map);
			return list;
		} catch (Exception e) {
			throw new Exception("评论列表获取失败"+e.getMessage());
		}
	}
	
	@Autowired
	CommentsMapper commentsMapper;
	//删除评论
	public int deleteComment(String com_id) throws Exception{
		try {
			int id = Integer.parseInt(com_id);
			return commentsMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new Exception("评论删除失败"+e.getMessage());
		}
	}
	
	//获取学分列表
	public ArrayList<Map<String,Object>> getScoreList(String act_id) throws Exception{
		try {
			int actid = Integer.parseInt(act_id);
			return orgnizationToolMapper.getScoreList(actid);
		} catch (Exception e) {
			throw new Exception("学分列表获取失败"+e.getMessage());
		}
	}
	
	//对选手进行评分
	public int grade(String score_id,String stu_id,String act_id) throws Exception{
		try {
			int scoreid = Integer.parseInt(score_id);
			int stuid = Integer.parseInt(stu_id);
			int actid = Integer.parseInt(act_id);
			//判断获奖人数
			int max = orgnizationToolMapper.getScoreMax(scoreid);
			int sum = orgnizationToolMapper.getScoreNum(scoreid);
			if(sum==max) throw new Exception("奖项达到人数上限!");
			//最终操作
			return orgnizationToolMapper.grade(scoreid, stuid, actid);
		} catch (Exception e) {
			throw new Exception("评分操作失败"+e.getMessage());
		}
	}
	
	@Autowired
	ActinfosMapper actinfosMapper;
	@Autowired
	Act_scoreMapper act_scoreMapper;
	@Autowired
	Act_examineMapper act_examineMapper;
	//发布活动
	public int publishActivity(JSONObject data) throws Exception{
		try {
			Activity act = new Activity();
			Actinfos actinfo = new Actinfos();
			Act_score as = new Act_score();
			Act_examine ae = new Act_examine();
			//构建活动类
			int act_id = activityMapper.getLastActID()+1;
			act.setId(act_id);
			act.setImg(" ");//存储图片，需要修改
			act.setIsteam(data.getBoolean("isteam"));
			act.setName(data.getString("name"));
			int orgid = orgnizationToolMapper.getIDbyOrgID(data.getString("org_id"));
			act.setOrgId(orgid);
			Date posttime = new Date();
			act.setPosttime(posttime);
			Date starttime = new Date(data.getString("starttime"));
			act.setStarttime(starttime);
			act.setType(data.getString("type"));
			act.setVisible(false);
			activityMapper.insert(act);
			//构建活动的详细信息
			actinfo.setActId(act_id);
			Date endtime = new Date(data.getString("endtime"));
			actinfo.setEndtime(endtime);
			actinfo.setMax(data.getInt("max"));
			actinfo.setMessage(data.getString("message"));
			if(act.getIsteam()) {
				actinfo.setTmax(data.getInt("tmax"));
				actinfo.setTmin(data.getInt("tmin"));
			}
			actinfosMapper.insert(actinfo);
			//构造活动的学分类型
			JSONArray score = data.getJSONArray("score");
			JSONObject obj;
			for(Object ob:score) {
				obj = JSONObject.fromObject(ob);
				as.setActId(act_id);
				as.setLevel(obj.getString("level"));
				as.setMax(obj.getInt("max"));
				as.setScore((float)obj.getDouble("score"));
				as.setType(obj.getString("type"));
				act_scoreMapper.insert(as);
			}
			//插入审核项目
			ae.setActId(act_id);
			ae.setPosttime(posttime);
			ae.setResult("待审核");
			act_examineMapper.insert(ae);
			//操作结束
			return 0;
		} catch (Exception e) {
			throw new Exception("活动发布失败"+e.getMessage());
		}
	}
}
