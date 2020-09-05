package com.learn.zqw.association;

import com.learn.zqw.association.domain.City;
import com.learn.zqw.association.mapper.CityMapper;
import lombok.Cleanup;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;

/**
 * //TODO
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2020/9/4 20:58
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class AssociationTests {

    @Test
    public void test() throws IOException {
        @Cleanup InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession session = sessionFactory.openSession();
        CityMapper mapper = session.getMapper(CityMapper.class);
        City city = mapper.selectWithProvinceById(1);
        System.out.println(city);
    }

    @Test
    public void test2() throws IOException {
        @Cleanup InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession session = sessionFactory.openSession();
        CityMapper mapper = session.getMapper(CityMapper.class);
        City city = mapper.selectWithProvinceById2(1);
        System.out.println(city);
    }
}
