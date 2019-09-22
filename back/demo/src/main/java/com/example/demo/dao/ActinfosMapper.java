package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Actinfos;

@Mapper
public interface ActinfosMapper {
    int deleteByPrimaryKey(Integer actId);

    int insert(Actinfos record);

    int insertSelective(Actinfos record);

    Actinfos selectByPrimaryKey(Integer actId);

    int updateByPrimaryKeySelective(Actinfos record);

    int updateByPrimaryKeyWithBLOBs(Actinfos record);

    int updateByPrimaryKey(Actinfos record);
}