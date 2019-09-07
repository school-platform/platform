package com.example.demo.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.Application;
import com.example.demo.controller.OrgnizationController;
import com.example.demo.dao.tooldao.OrgnizationToolMapper;
import com.example.demo.services.OrgnizationService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrgTest {
	@Autowired
	OrgnizationService service;
	@Autowired
	OrgnizationToolMapper mapper;
	
	@Ignore
	@Test
	public void test() throws Exception {
		System.out.println(service.getOrginfos("17718260001"));
	}
	
	@Ignore
	@Test
	public void testAlterPhone()throws Exception{
		System.out.println(service.alterphone("17718260001", "15055412599"));
	}
	
	@Ignore
	@Test
	public void testGetVipLst()throws Exception{
		System.out.println(service.getVipList("17718260001", 1, 5));
	}
	
	@Ignore
	@Test
	public void testAddVip() throws Exception{
		System.out.println(mapper.isVip(200,1)==null);
	}
	
	@Ignore
	@Test
	public void testDetailAct() throws Exception{
		System.out.println(service.getActDetail("51"));
	}
	
	@Ignore
	@Test
	public void testMembers() throws Exception{
//		ArrayList<Map<String,Object>> list = mapper.getTeamMember(1);
//		System.out.println(list);
		System.out.println(service.getRegisterList("51", 1, 5));
	}
	
	@Ignore
	@Test
	public void TestGetMsg() throws Exception{
		System.out.println(service.getOrgMsg("17718260001"));
	}
	
	@Ignore
	@Test
	public void TestCommen() throws Exception{
		System.out.println(service.getCommenList("51", 1, 5));
	}
	
	@Test
	public void testAddActivity() throws Exception{
		JSONObject json = new JSONObject();
		json.put("isteam", false);
		json.put("name", "配音活动");
		json.put("org_id", "17718264651");
		json.put("starttime", "2019/12/12");
		json.put("type", "B模块");
		json.put("endtime", "2019/12/30");
		json.put("max", "1000");
		json.put("message", "配音活动");
		
		//构造第一个学分
		JSONArray ja = new JSONArray();
		JSONObject score = new JSONObject();
		score.put("level", "1");
		score.put("max", 1000);
		score.put("score", 3.0);
		score.put("type", "B");
		ja.add(score);
		
		//将学分加入测试对象
		json.put("score", ja);
		
		service.publishActivity(json);
	}
}
