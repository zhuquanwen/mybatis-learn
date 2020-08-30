package com.learn.zqw.sqlannotation.mapper;

import com.learn.zqw.sqlannotation.domain.TestData;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TestDataMapper {

    @Select("select * from test_data")
    List<TestData> findAll(RowBounds rowBounds);
}
