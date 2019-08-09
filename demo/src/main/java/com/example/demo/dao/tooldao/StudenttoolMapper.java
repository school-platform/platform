package com.example.demo.dao.tooldao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudenttoolMapper {
	List<Map<String,Object>> selectScoreInfo(int stu_id,String type); 
}
