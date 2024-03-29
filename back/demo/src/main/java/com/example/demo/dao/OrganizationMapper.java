package com.example.demo.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Organization;

@Mapper
public interface OrganizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
    
    int getLastID();
    
    ArrayList<Integer> getAllID();
}