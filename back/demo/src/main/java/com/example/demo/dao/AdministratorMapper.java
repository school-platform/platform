package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Administrator;

@Mapper
public interface AdministratorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    Administrator selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);
}