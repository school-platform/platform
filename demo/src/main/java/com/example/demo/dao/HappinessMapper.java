package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Happiness;

@Mapper
public interface HappinessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Happiness record);

    int insertSelective(Happiness record);

    Happiness selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Happiness record);

    int updateByPrimaryKey(Happiness record);
}