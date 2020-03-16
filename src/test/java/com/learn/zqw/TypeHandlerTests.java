package com.learn.zqw;

import com.learn.zqw.generator.domain.Bird;
import com.learn.zqw.generator.domain.BirdExample;
import com.learn.zqw.generator.domain.SexEnum;
import com.learn.zqw.generator.mapper.BirdMapper;
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

/**
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2020/3/16 22:14
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class TypeHandlerTests {

    /**
     * 测试查询所有
     * */
    @Test
    public void test() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        BirdMapper mapper = session.getMapper(BirdMapper.class);
        BirdExample birdExample = new BirdExample();
        List<Bird> birds = mapper.selectByExample(birdExample);
        Assert.assertNotNull(birds);
        if (birds != null) {
            birds.forEach(System.out::println);
        }

        Bird bird = new Bird();
        bird.setName("小紫");
        bird.setSex(SexEnum.MALE);
        mapper.insert(bird);
        session.commit();

    }
}
