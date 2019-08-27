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
		System.out.println(service.getOrginfos("16111205000"));
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
		System.out.println(service.addVip("17718260001", "16111205140"));
	}
	
	@Ignore
	@Test
	public void testDetailAct() throws Exception{
		System.out.println(service.getActDetail("51"));
	}
	
	@Test
	public void testMembers() throws Exception{
//		ArrayList<Map<String,Object>> list = mapper.getTeamMember(1);
//		System.out.println(list);
		System.out.println(service.getRegisterList("51", 1, 5));
	}
}
