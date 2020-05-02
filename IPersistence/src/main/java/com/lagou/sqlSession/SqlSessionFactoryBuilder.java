package com.lagou.sqlSession;

import com.lagou.config.XMLConfigBuilder;
import com.lagou.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream in) throws PropertyVetoException, DocumentException {
        //使用dom4j解析配置文件,将解析出来的内容封装到configuration对象中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.praseConfig(in);
        //创建SqlSessionFactory对象 工厂类：生产sqlSession 回话对象
        DefaultSessionfactory defaultSessionfactory = new DefaultSessionfactory(configuration);
        return  defaultSessionfactory;
    }
}
