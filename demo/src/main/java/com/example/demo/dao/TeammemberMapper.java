package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Teammember;

@Mapper
public interface TeammemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teammember record);

    int insertSelective(Teammember record);

    Teammember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teammember record);

    int updateByPrimaryKey(Teammember record);
}