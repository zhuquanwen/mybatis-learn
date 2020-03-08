package com.learn.zqw.sqlannotation;

import com.learn.zqw.sqlannotation.domain.Book;
import com.learn.zqw.sqlannotation.domain.Library;
import com.learn.zqw.sqlannotation.mapper.BookMapper;
import com.learn.zqw.sqlannotation.mapper.LibraryMapper;
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

/**
 * 测试注解方式SQL执行
 *
 * @author zhuquanwen
 * @vesion 1.0
 * @date 2020/3/8 12:11
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class BookTests {

    /**
     * 测试查询所有
     * */
    @Test
    public void test() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        List<Book> books = mapper.findAll();
        Assert.assertNotNull(books);
        if (books != null) {
            books.forEach(System.out::println);
        }
    }

    /**
     * 测试按照ID查询，并关联一个主表记录，且自定义result
     * */
    @Test
    public void test2() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        Book book = mapper.findById(1);
        Assert.assertNotNull(book);
        System.out.println(book);
        Book book1 = new Book();
        book1.setId(2);
        Book book2 = mapper.findById(book1);
        Assert.assertNotNull(book2);
        System.out.println(book2);
    }

    /**
     * 测试使用XML中的插入方法,并且为嵌套插入
     *
     * */
    @Test
    public void test3() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        Book book = new Book();
        book.setName("离散数学");
        book.setNum(400);
        LibraryMapper libraryMapper = session.getMapper(LibraryMapper.class);
        Library library = libraryMapper.findById(1);
        book.setLibrary(library);
        int inserted = mapper.insert(book);
        Assert.assertEquals(1, inserted);
        System.out.println(inserted);
        session.commit();
    }

    /**
     * 测试注解条件查询
     * */
    @Test
    public void test4() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        Book book = new Book();
        book.setName("C");
        book.setNum(10);
        List<Book> books = mapper.findByCondition(book);
        Assert.assertNotNull(books);
        books.forEach(System.out::println);
    }

    /**
     * 测试使用注解中的插入方法,并且为嵌套插入
     *
     * */
    @Test
    public void test5() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        Book book = new Book();
        book.setName("大数据时代");
        book.setNum(300);
        LibraryMapper libraryMapper = session.getMapper(LibraryMapper.class);
        Library library = libraryMapper.findById(1);
        book.setLibrary(library);
        int inserted = mapper.insert2(book);
        Assert.assertEquals(1, inserted);
        System.out.println(inserted);
        session.commit();
    }

    /**
     * 测试使用注解中的修改入方法
     *
     * */
    @Test
    public void test6() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        Book book = mapper.findById(1);
        book.setName("C语言程序设计V2");
        int updated = mapper.update(book);
        Assert.assertEquals(1, updated);
        System.out.println(updated);
        session.commit();
    }

    /**
     * 测试使用注解中删除方法
     *
     * */
    @Test
    public void test7() throws IOException {
        @Cleanup InputStream in = Resources.getResourceAsStream ("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        BookMapper mapper = session.getMapper(BookMapper.class);
        Book book = new Book();
        book.setName(UUID.randomUUID().toString());
        book.setNum(300);
        LibraryMapper libraryMapper = session.getMapper(LibraryMapper.class);
        Library library = libraryMapper.findById(1);
        book.setLibrary(library);
        int inserted = mapper.insertReturnId(book);
        session.commit();
        Assert.assertEquals(1, inserted);

        int delete = mapper.delete(book.getId());
        Assert.assertEquals(1, delete);
        session.commit();
    }
}
