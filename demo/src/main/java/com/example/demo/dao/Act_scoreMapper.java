package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Act_score;

@Mapper
public interface Act_scoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Act_score record);

    int insertSelective(Act_score record);

    Act_score selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Act_score record);

    int updateByPrimaryKey(Act_score record);
}