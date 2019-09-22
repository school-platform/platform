package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Activity;

@Mapper
public interface ActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
    
    int getOrgid(int act_id);
    
    int getLastActID();
}