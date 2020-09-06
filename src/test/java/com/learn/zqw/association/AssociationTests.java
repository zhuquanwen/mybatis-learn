package com.learn.zqw.association;

import com.learn.zqw.association.domain.City;
import com.learn.zqw.association.domain.MyProvince;
import com.learn.zqw.association.domain.Province;
import com.learn.zqw.association.mapper.CityMapper;
import com.learn.zqw.association.mapper.ProvinceMapper;
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

    /**
     * 嵌套(分步)关联查询
     * */
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

    /**
     * 嵌套结果(单步)关联查询
     * */
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

    /**
     * 一对多嵌套结果(单步)关联查询
     * */
    @Test
    public void test3() throws IOException {
        @Cleanup InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession session = sessionFactory.openSession();
        ProvinceMapper mapper = session.getMapper(ProvinceMapper.class);
        MyProvince province = mapper.selectWithCitysById(1);
        System.out.println(province);
    }

    /**
     * 一对多嵌套(多步)关联查询
     * */
    @Test
    public void test4() throws IOException {
        @Cleanup InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession session = sessionFactory.openSession();
        ProvinceMapper mapper = session.getMapper(ProvinceMapper.class);
        MyProvince province = mapper.selectWithCitysById0(1);
        System.out.println(province);
    }
}
