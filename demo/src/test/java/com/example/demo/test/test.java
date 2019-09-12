package com.example.demo.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.Application;
import com.example.demo.dao.StudentsMapper;
import com.example.demo.dao.tooldao.AdministratorToolMapper;
import com.example.demo.dao.tooldao.testMapper;
import com.example.demo.services.AdministratorService;
import com.example.demo.services.StudentsService;

import net.sf.json.JSONObject;;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class test {

	@Autowired
	AdministratorService service;
	
	@Autowired
	AdministratorToolMapper mapper;
	
	@Autowired
	StudentsService studentService;
	
	@Ignore
	@Test
	public void testAddStu() throws Exception {
		JSONObject json = new JSONObject();
		json.put("name", "SilverRice");
		json.put("password", "silverrice");
		json.put("stu_id","16111205999");
		json.put("birthday", "2018/05/20");
		json.put("col_id", "0000");
		json.put("grade", 2016);
		json.put("major_id", 1);
		json.put("message","你好测试");
		json.put("phone", "17718264651");
		json.put("islogin", false);
		service.addStudent(json);
	}
	
	@Ignore
	@Test
	public void testAddOrgnization() throws Exception{
		JSONObject json = new JSONObject();
		json.put("name", "广播台");
		json.put("org_id", "17718264651");
		json.put("password", "17718264651");
		json.put("foundtime", "1954/09/01");
		json.put("contact", "632004415");
		json.put("leadername", "SilverRice");
		json.put("message", "安徽师范大学广播台");
		json.put("col_id", "0000");
		service.addOrgnization(json);
	}
	
	@Test
	public void testGetMyAct() throws Exception{
		System.out.println(0);
		System.out.println(studentService.getMyAct("16111205001",0,5));
	}

}
