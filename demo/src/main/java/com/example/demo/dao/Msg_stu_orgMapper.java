package com.example.demo.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Msg_stu_org;

@Mapper
public interface Msg_stu_orgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Msg_stu_org record);

    int insertSelective(Msg_stu_org record);

    Msg_stu_org selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Msg_stu_org record);

    int updateByPrimaryKey(Msg_stu_org record);
    
    ArrayList<Msg_stu_org> selectByStuID(int stu_id);
    
    int deleteByStuIDMSGID(int stu_id,int msg_id);
    
    int deleteByStuID(int stu_id);
    
    Msg_stu_org selectByStuMsg(int stu_id,int msg_id);
}