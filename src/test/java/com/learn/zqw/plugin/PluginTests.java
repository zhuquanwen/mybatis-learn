package com.learn.zqw.plugin;

import com.learn.zqw.generator.domain.Student;
import com.learn.zqw.generator.mapper.StudentMapper;
import lombok.Cleanup;
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

/**
 * //TODO
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2020/6/27 14:05
 * @since jdk1.8
 */

@RunWith(JUnit4.class)
public class PluginTests {
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
}
