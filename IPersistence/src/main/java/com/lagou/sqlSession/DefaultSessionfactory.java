package com.lagou.sqlSession;

import com.lagou.pojo.Configuration;

public class DefaultSessionfactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSessionfactory(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
