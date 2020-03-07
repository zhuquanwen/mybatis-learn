package com.learn.zqw.generator;

import com.learn.zqw.generator.domain.Student;
import com.learn.zqw.generator.domain.StudentExample;
import com.learn.zqw.generator.mapper.StudentMapper;
import lombok.Cleanup;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * //TODO
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2020/3/7 15:49
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class GeneratorTests {

    /**
     * 测试按照ID查询
     * */
    @Test
    public void Test1() throws IOException {
        //1.读取配置文件
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        //2. 创建SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        //按主键查询
        Student student = mapper.selectByPrimaryKey(1);
        Assert.assertNotNull(student);
        System.out.println(student);
    }

    /**
     * 使用Example查询
     * */
    @Test
    public void Test2() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        StudentExample example = new StudentExample();
        //将条件封装到createCriteria集合中  -条件列表
        example.createCriteria().andNameEqualTo("zhangsan").andRealNameIsNotNull();

        //按条件查询
        List<Student> list = mapper.selectByExample(example);
        System.out.println("按条件查询:");
        Assert.assertNotNull(list);
        list.forEach(System.out::println);

    }

    /**
     * 测试条件插入
     * */
    @Test
    public void Test3() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        Student student = new Student() ;
        student.setName(UUID.randomUUID().toString());
        int i = mapper.insertSelective(student);
        session.commit();
        Assert.assertEquals(1, i);
    }

    /**
     * 按照Example求条目
     * */
    @Test
    public void Test4() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        StudentExample example = new StudentExample();
        example.createCriteria().andIdBetween(1, 200).andNameIn(Arrays.asList("zhangsan", "lisi"));
        long l = mapper.countByExample(example);
        Assert.assertEquals(2, l);
    }

    /**
     * 测试按照Example删除
     * */
    @Test
    public void Test5() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        Student student = new Student();
        String name = UUID.randomUUID().toString();
        student.setName(name);
        mapper.insertSelective(student);
        session.commit();
        StudentExample example = new StudentExample();
        example.createCriteria().andNameEqualTo(name);
        int i = mapper.deleteByExample(example);
        Assert.assertEquals(1, i);
        session.commit();

    }

    /**
     * 测试按照ID删除
     * */
    @Test
    public void Test6() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        Student student = new Student();
        String name = UUID.randomUUID().toString();
        student.setName(name);
        student.setId(1001);
        mapper.insertSelective(student);
        session.commit();
        StudentExample example = new StudentExample();
        example.createCriteria().andNameEqualTo(name);
        int i = mapper.deleteByPrimaryKey(1001);
        Assert.assertEquals(1, i);
        session.commit();

    }

    /**
     * 测试普通插入
     * */
    @Test
    public void Test7() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        Student student = new Student();
        student.setName(UUID.randomUUID().toString());
        student.setRealName("测试名字");
        mapper.insert(student);
        session.commit();
    }

    /**
     * 测试条件修改，并使用example
     * */
    @Test
    public void Test8() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        Student student = new Student();
        student.setRealName("测试名字");

        StudentExample example = new StudentExample();
        example.createCriteria().andIdGreaterThan(3);
        mapper.updateByExampleSelective(student, example);
        session.commit();
    }

    /**
     * 测试普通修改，并使用example
     * */
    @Test
    public void Test9() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        StudentExample example = new StudentExample();
        example.createCriteria().andIdGreaterThan(100);


        List<Student> students = mapper.selectByExample(example);
        Student student = students.get(0);
        student.setRealName("测试名字22");

        mapper.updateByExample(student, example);
        session.commit();
    }

    /**
     * 测试条件修改，并使用Id
     * */
    @Test
    public void Test10() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        StudentExample example = new StudentExample();
        example.createCriteria().andIdGreaterThan(100);


        List<Student> students = mapper.selectByExample(example);
        Student student = students.get(0);
        student.setRealName("测试名字33");

        mapper.updateByPrimaryKeySelective(student);
        session.commit();
    }

    /**
     * 测试普通修改，并使用Id
     * */
    @Test
    public void Test11() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        StudentExample example = new StudentExample();
        example.createCriteria().andIdGreaterThan(100);

        List<Student> students = mapper.selectByExample(example);
        Student student = students.get(0);
        student.setRealName(null);

        mapper.updateByPrimaryKey(student);
        session.commit();
    }

    /**
     * 测试查询并排序
     * */
    @Test
    public void Test12() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        StudentExample example = new StudentExample();
        example.createCriteria().andIdGreaterThan(0);
        example.setOrderByClause("name asc");

        List<Student> students = mapper.selectByExample(example);
        students.forEach(System.out::println);
        session.commit();
    }

    /**
     * 测试自定义的排序
     * */
    @Test
    public void Test13() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);

        StudentExample example = new StudentExample();
        example.createCriteria().andIdGreaterThan(0);
        example.setOrderByClause("name asc");
        example.setLimit("0, 3");

        List<Student> students = mapper.selectByExample(example);
        students.forEach(System.out::println);
        session.commit();
    }

}
