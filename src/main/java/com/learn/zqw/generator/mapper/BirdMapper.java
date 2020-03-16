package com.learn.zqw.generator.mapper;

import com.learn.zqw.generator.domain.Bird;
import com.learn.zqw.generator.domain.BirdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BirdMapper {
    long countByExample(BirdExample example);

    int deleteByExample(BirdExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Bird record);

    int insertSelective(Bird record);

    List<Bird> selectByExample(BirdExample example);

    Bird selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Bird record, @Param("example") BirdExample example);

    int updateByExample(@Param("record") Bird record, @Param("example") BirdExample example);

    int updateByPrimaryKeySelective(Bird record);

    int updateByPrimaryKey(Bird record);
}