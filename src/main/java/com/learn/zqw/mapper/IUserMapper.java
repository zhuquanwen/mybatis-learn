package com.learn.zqw.mapper;

import com.learn.zqw.domain.User;

import java.util.List;

public interface IUserMapper {


    List<User> findAll();
    int insert(User user);
    Integer insertAndGetId(User user);
    Integer insertAndGetId2(User user);
    int insertSelective(User user);
    int updateByPrimaryKeySelective(User user);
    int updateByPrimaryKey(User user);
    User selectByPrimaryKey(Integer id);
    int deleteByPrimaryKey(Integer id);

}
