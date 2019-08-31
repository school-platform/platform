package com.example.demo.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.Application;
import com.example.demo.dao.StudentsMapper;
import com.example.demo.dao.tooldao.testMapper;;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class test {

	@Autowired
	testMapper mapper;
	
	@Autowired
	StudentsMapper stumapper;
	
	@Test
	public void test() {
		System.out.println(mapper.getIDbyOrgID("17718260001"));
	}

}
