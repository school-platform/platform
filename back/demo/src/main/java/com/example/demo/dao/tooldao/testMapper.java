package com.example.demo.dao.tooldao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface testMapper {
	int getIDbyOrgID(String org_id);
}
