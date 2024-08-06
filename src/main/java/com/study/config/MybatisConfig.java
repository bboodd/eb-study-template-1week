package com.study.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MybatisConfig {
    public static SqlSessionFactory sqlSession;

    static {
        String resource = "config/mybatis-config.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSession = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (Exception e) {
            System.out.println("MabatisConfig err" + e);
        }
    }

    public static SqlSessionFactory getSqlSession(){
        return sqlSession;
    }
}
