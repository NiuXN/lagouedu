package com.lagou.config;

import com.lagou.io.Resources;
import com.lagou.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class XMLConfigBuilder {
    /**
     * 将配置文件进行解析，封装到configuration 对象中
     */

    private Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration=new Configuration();
    }

    public Configuration praseConfig(InputStream in) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(in);
        //根对象目前就代表(<configuration>)标签
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");
        //创建一个properties存放数据源配置信息
        Properties properties = new Properties();

        //获取list 集合中的 数据源连接信息
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }
        //创建一个c3p0连接池 进行连接
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driver"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("url"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(comboPooledDataSource);
    //找出sqlMapConfig.xml 配置mapper标签中的路径==> 将xml文件加载成字节流
        List<Element> mapperList = rootElement.selectNodes("//mapper");
        for (Element element : mapperList) {
            String mapPath = element.attributeValue("Resource");
            //拿到输入流
            InputStream resourcesAsStrem = Resources.getResourcesAsStrem(mapPath);
            //创建一个解析 *mapper.xml文件 传入 configuration 对象
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.prase(resourcesAsStrem);
        }
        return configuration;
    }
}
