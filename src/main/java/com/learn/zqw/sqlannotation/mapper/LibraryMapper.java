package com.learn.zqw.sqlannotation.mapper;

import com.learn.zqw.sqlannotation.domain.Book;
import com.learn.zqw.sqlannotation.domain.Library;
import org.apache.ibatis.annotations.Select;

public interface LibraryMapper {

    @Select("select * from library where id = #{id}")
    Library findById(int id);

    int insert(Book book);

}
