package com.learn.zqw;

import com.learn.zqw.domain.User;
import com.learn.zqw.mapper.IUserMapper;
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
import java.util.List;
import java.util.UUID;

@RunWith(JUnit4.class)
public class BaseTest {

    /**
     * 测试基本Mybatise的运行
     * */
    @Test
    public void baseTest() throws IOException {
        //1.读取配置文件
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        //2. 创建SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        @Cleanup SqlSession session = factory.openSession ();
        //4.使用SqlSession创建Mapper接口的代理对象
        IUserMapper userMapper = session.getMapper(IUserMapper.class);


        //5.使用代理对象执行分法

        //1)获取所有数据
        List<User> users = userMapper.findAll();
        Assert.assertNotNull(users);
        System.out.println("=========查询所有的结果======");
        if (users != null) {
            users.forEach(System.out::println);
        }
        System.out.println("=========查询所有的结果End======");

        //2)插入记录
        int insertResult = userMapper.insert(new User(UUID.randomUUID().toString(), "123456"));
        Assert.assertEquals(1, insertResult);

        //3)插入记录返回ID
        User user1 = new User(UUID.randomUUID().toString(), "123456");
        int getId = userMapper.insertAndGetId(user1);
        System.out.printf("插入并返回ID：[%d]\n", user1.getId());
        Assert.assertNotNull(user1.getId());


        //4)插入记录返回ID2
        User user2 = new User(UUID.randomUUID().toString(), "123456");
        int getId2 = userMapper.insertAndGetId2(user2);
        System.out.printf("插入并返回ID：[%d]\n", user2.getId());
        Assert.assertNotNull(user2.getId());

        //5)按条件插入
        User user3 = new User();
        user3.setUsername(UUID.randomUUID().toString());
        int selectiveInsertId = userMapper.insertSelective(user3);
        Assert.assertEquals(1, selectiveInsertId);
        Assert.assertNull(user3.getPassword());

        //6)按ID查询
        User user = userMapper.selectByPrimaryKey(1);
        Assert.assertNotNull(user);

        //7)按条件修改
        user.setUsername(null);
        user.setPassword("1234567");
        int x = userMapper.updateByPrimaryKeySelective(user);
        Assert.assertEquals(1, x);

        //8)全部修改
        User user4 = userMapper.selectByPrimaryKey(1);
        user4.setPassword("12345678");
        int y = userMapper.updateByPrimaryKey(user4);
        Assert.assertEquals(1, y);
        session.commit();

        //9)删除,先删了再回滚下
        int i = userMapper.deleteByPrimaryKey(1);
        Assert.assertEquals(1, i);
        session.rollback();

    }
}
