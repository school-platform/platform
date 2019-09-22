package com.example.demo.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Students;

@Mapper
public interface StudentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Students record);

    int insertSelective(Students record);

    Students selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Students record);

    int updateByPrimaryKey(Students record);
    
    Students selectByStuID(String stu_id);
    
    int getLastID();
    
    int getIDByStuID(String stu_id);
    
    ArrayList<Integer> getAllID();
}