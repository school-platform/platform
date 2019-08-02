package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Act_participant;

@Mapper
public interface Act_participantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Act_participant record);

    int insertSelective(Act_participant record);

    Act_participant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Act_participant record);

    int updateByPrimaryKey(Act_participant record);
    
    Act_participant selectByStuAct(int stu_id,int act_id);
}