package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Studentsinfos;

@Mapper
public interface StudentsinfosMapper {
    int deleteByPrimaryKey(Integer studentid);

    int insert(Studentsinfos record);

    int insertSelective(Studentsinfos record);

    Studentsinfos selectByPrimaryKey(Integer studentid);

    int updateByPrimaryKeySelective(Studentsinfos record);

    int updateByPrimaryKey(Studentsinfos record);
}