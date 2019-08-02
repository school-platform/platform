package com.example.demo.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Org_student;

@Mapper
public interface Org_studentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Org_student record);

    int insertSelective(Org_student record);

    Org_student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Org_student record);

    int updateByPrimaryKey(Org_student record);
    
    ArrayList<Org_student> selectByStuID(int stu_id);
}