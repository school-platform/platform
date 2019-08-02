package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Orginfos;

@Mapper
public interface OrginfosMapper {
    int deleteByPrimaryKey(Integer orgid);

    int insert(Orginfos record);

    int insertSelective(Orginfos record);

    Orginfos selectByPrimaryKey(Integer orgid);

    int updateByPrimaryKeySelective(Orginfos record);

    int updateByPrimaryKey(Orginfos record);
}