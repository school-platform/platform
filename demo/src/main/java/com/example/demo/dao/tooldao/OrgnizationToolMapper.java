package com.example.demo.dao.tooldao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrgnizationToolMapper {
	int getIDbyOrgID(String org_id);
	public ArrayList<Map<String,Object>> getOrgInfo(String org_id);//请求社团详细信息
	public int alterphone(String org_id,String phone);//修改社团联系方式
	public ArrayList<Map<String,Object>> getVipList(String org_id,int firstnum,int num);//获取会员列表
	public int addVip(int org_id,int stu_id,Date jointime);//添加会员
	public int deleteVip(int org_id,int stu_id);//删除会员
	public Map<String,Object> isInOrg(int org_id,int stu_id);//查询学生是否在社团中
	public ArrayList<Map<String,Object>> getActList(int org_id,int snum,int num);//获取活动列表
	public ArrayList<Map<String,Object>> getOrgMsg(int org_id);//获取信息列表
	public int deleteMsgByOrgMsg(String org_id,String msg_id);//删除消息
	public int readMsg(boolean state,int msg_id,int org_id);//学生阅读消息
	public boolean msgIsRead(int msg_id,int org_id);//查询消息状态
	public ArrayList<Map<String,Object>> getActInfos(int act_id);//获取活动详细信息01
	public ArrayList<Map<String,Object>> getActScores(int act_id);//获取活动详细信息02（学分）
	public ArrayList<Map<String,Object>> getFirstMembers(int act_id,int snum,int num);//查询活动的一级报名人员
	public boolean isTeam(int act_id);//查询互动是否为组队互动
	public ArrayList<Map<String,Object>> getTeamMember(int ap_id);//根据团队id查询队员信息
	public ArrayList<Map<String,Object>> getSignList(int act_id,int snum,int num);//获取签到列表
	public int checkjoin(Date jointime,int act_id,int stu_id);//给同学报名通过
	public int deleteJoin(int act_id,int stu_id);//删除同学的报名信息
	public int checkSign();//给同学签到通过
	public ArrayList<Map<String,Object>> getComList(int act_id,int snum,int num);//获取评论列表
	public String getComText(int com_id);//获取评论内容
	public ArrayList<Map<String,Object>> getScoreList(int act_id);//获取学分列表
	public int grade(int score_id,int stu_id,int act_id);//对选手进行评分
	public int getScoreNum(int score_id);//统计获奖人数
	public int getScoreMax(int score_id);//获取奖项人数上限
	int getCountStu(int org_id);//请求社团中所有的学生个数
	int getCountActivity(int org_id);//请求社团中的所有活动个数
	int getCountParticipant(int act_id);//获得活动中参与人员总数
	int getConuntCommen(int act_id);//获取活动的评论总数
	Map<String,Object> isVip(int stu_id,int org_id);//判断学生是不是会员
	int upPass(String password,String org_id);//社团修改密码
	String getPass(String org_id);//获取社团的密码
}
