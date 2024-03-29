package com.example.demo.dao.tooldao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudenttoolMapper {
	List<Map<String,Object>> selectScoreInfo(int stu_id,String type); 
	int getIDByStudentID(String studentid);
	int upPass(String password,String stu_id);
	boolean isTeamAct(String act_id);//获取互动是否为组队活动
	Map<String,Object> getMyPartInfo(String act_id,String stu_id);//获取一级参与信息
	Map<String,Object> getMyPartInfoInTeam(String act_id,String stu_id);//从团队中获取参与信息
	int confirmTm(Date jointime,int act_id,int stu_id);//同意入团
	Map<String,Object> getLeaderMap(int stu_id,int act_id);//获取leader的信息
	int cancel(int team_id,int stu_id);//删除队员申请
	ArrayList<Map<String,Object>> getNewAct(int snum,int num);//获取最新活动s
	ArrayList<Map<String,Object>> getHotAct(int snum,int num);//获取热门活动
	String getPass(String stu_id);//获取对应学生的密码
	ArrayList<Map<String,Object>> getMyAct(int stu_id,int now,int count);//获取我的活动
	int isLeader(int stu_id,int act_id);//判断学生是不是领队
	int isTeamember(int stu_id,int act_id);//判断学生是不是队员
	int getSumAct();//获取活动总数
	int loginCheck(String stu_id,String pass);//登陆验证
}
