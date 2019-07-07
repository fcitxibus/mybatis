package com.lion.mybatis.until;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisSessionFactory {
    private static final String CONFIG_FILE = "mybatis/mybatis.cfg.xml" ;
    private static SqlSessionFactory sessionFactory ;
    private static final ThreadLocal<SqlSession> SESSION_THREAD_LOCAL = new ThreadLocal<>() ;
    static { // 在类加载的时候进行SqlSessionFactory类的对象实例化
        buildSqlSessionFactory() ;
    }
    private static SqlSessionFactory buildSqlSessionFactory () {
        try {
            InputStream input = Resources.getResourceAsStream(CONFIG_FILE);
            sessionFactory = new SqlSessionFactoryBuilder().build(input) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory ;
    }
    public static SqlSession getSession() {
        SqlSession session = SESSION_THREAD_LOCAL.get() ;
        if (session == null) {
            session = sessionFactory.openSession() ;
            SESSION_THREAD_LOCAL.set(session);
        }
        return session ;
    }
    public static void close() {
        SqlSession session = SESSION_THREAD_LOCAL.get() ;
        if (session != null) {
            session.close();
            SESSION_THREAD_LOCAL.remove();
        }
    }
    /**
     * 获取SqlSessionFactory接口实例
     * @return 返回一个sessionFactory
     */
    public static SqlSessionFactory getSessionFactory() {
        return sessionFactory ;
    }
}
