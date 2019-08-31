package com.example.demo.dao.tooldao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AdministratorToolMapper {
	public ArrayList<Map<String,Object>> getAllCollege();//获取全部学院
	public ArrayList<Map<String,Object>> getMajorByCollege(int col_id);//获取学院中的专业
	public ArrayList<Map<String,Object>> getAllStudent(int snum,int num);//获取学生列表
	public ArrayList<Map<String,Object>> getAllOrgnization(int snum,int num);//获取社团列表
	public ArrayList<Map<String,Object>> getAllExamine(int snum,int num);//获取活动审核列表
	public int examine(Date extime,String result,int id);//审核
	public Map<String,Object> getActInfo(int act_id);//获取活动的详细信息
	public ArrayList<Map<String,Object>> getScoreByAct(int act_id);//获取活动的学分详细信息
}