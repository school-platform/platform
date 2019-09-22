package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Comments;

@Mapper
public interface CommentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);
}