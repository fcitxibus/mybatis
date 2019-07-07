package com.lion.mybatis.test;


import com.lion.mybatis.until.MyBatisSessionFactory;
import com.lion.mybatis.vo.News;
import org.junit.Test;

public class TestConnectionA {
    @Test
    public void testAdd() throws Exception {
        News vo = new News();
        vo.setTitle("测试连接");
        vo.setContent("连接1号");
        System.out.println(MyBatisSessionFactory.getSession().insert("com.lion.mapper.NewsNS.doCreate", vo));
        MyBatisSessionFactory.getSession().commit();
        MyBatisSessionFactory.close();
    }
}
