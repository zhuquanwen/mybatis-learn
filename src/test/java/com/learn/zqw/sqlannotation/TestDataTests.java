package com.learn.zqw.sqlannotation;

import com.learn.zqw.sqlannotation.domain.Book;
import com.learn.zqw.sqlannotation.domain.Library;
import com.learn.zqw.sqlannotation.domain.TestData;
import com.learn.zqw.sqlannotation.mapper.BookMapper;
import com.learn.zqw.sqlannotation.mapper.LibraryMapper;
import com.learn.zqw.sqlannotation.mapper.TestDataMapper;
import lombok.Cleanup;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * 测试注解方式SQL执行
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2020/3/8 12:11
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class TestDataTests {

    /**
     * 测试查询所有
     * */
    @Test
    public void test() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        TestDataMapper mapper = session.getMapper(TestDataMapper.class);
        RowBounds rowBounds = new RowBounds(600000, 20);
        List<TestData> books = mapper.findAll(rowBounds);
        System.out.println(books.size());
        Assert.assertNotNull(books);
        if (books != null) {
            books.forEach(System.out::println);
        }
    }


}
