package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Act_examine;

@Mapper
public interface Act_examineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Act_examine record);

    int insertSelective(Act_examine record);

    Act_examine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Act_examine record);

    int updateByPrimaryKey(Act_examine record);
}