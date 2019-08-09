package com.example.demo.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Scoretype;

@Mapper
public interface ScoretypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Scoretype record);

    int insertSelective(Scoretype record);

    Scoretype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Scoretype record);

    int updateByPrimaryKey(Scoretype record);
    
    ArrayList<Scoretype> selectAll();
}