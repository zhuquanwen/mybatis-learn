package com.learn.zqw.sqlannotation.mapper;

import com.learn.zqw.sqlannotation.domain.Book;
import com.learn.zqw.sqlannotation.domain.Library;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface BookMapper {

    int insert(Book book);

    int insertReturnId(Book book);

    @Select("select * from book")
    List<Book> findAll();

    @Select("select * from book where id = #{id}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column = "name",property = "name"),
            @Result(column = "num",property = "num"),
            @Result(column = "library_id",property = "library",javaType = Library.class,
                    one = @One(select = "com.learn.zqw.sqlannotation.mapper.LibraryMapper.findById",fetchType = FetchType.LAZY)),
    })
    Book findById(int id);
    Book findById(Book book);


    @Select(value = {" <script>" +
            " SELECT * FROM book " +
            " <where> 1=1 " +
            " <if test=\"name != null\"> AND name like CONCAT('%', #{name}, '%')</if> " +
            " <if test=\"num != null\" >  AND num>#{num}</if> " +
            " </where>" +
            " </script>"})
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column = "name",property = "name"),
            @Result(column = "num",property = "num"),
            @Result(column = "library_id",property = "library",javaType = Library.class,
                    one = @One(select = "com.learn.zqw.sqlannotation.mapper.LibraryMapper.findById",fetchType = FetchType.LAZY)),
    })
    List<Book> findByCondition(Book book);

    @Insert("insert into book(name, num, library_id) values (#{name}, #{num}, #{library.id})")
    int insert2(Book book);

    @Update("update book set name = #{name} where id = #{id}")
    int update(Book book);

    @Delete("delete from book where id = #{id}")
    int delete(int id);
    int delete(Book id);


}
