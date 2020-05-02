package com.lagou.config;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {
    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }
    //读取输入流中的信息
    public void prase(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        //获取根根标签
        Element rootElement = document.getRootElement();
        //获取根根标签namespace的值
        String namespace = rootElement.attributeValue("namespace");
        List<Element> list = rootElement.selectNodes("//select");
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();//sql
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setResultType(resultType);
            mappedStatement.setSql(sqlText);
            //xml中的唯一标识  namespace+id
            configuration.getMapper().put(namespace+"."+id,mappedStatement);
        }
        //封装新增
        List<Element> insertList = rootElement.selectNodes("//insert");
        for (Element element : insertList) {
            String id = element.attributeValue("id");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();//sql
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            //xml中的唯一标识  namespace+id
            configuration.getMapper().put(namespace+"."+id,mappedStatement);
        }
        //删除
        List<Element> deleteList = rootElement.selectNodes("//delete");
        for (Element element : deleteList) {
            String id = element.attributeValue("id");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();//sql
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            //xml中的唯一标识  namespace+id
            configuration.getMapper().put(namespace+"."+id,mappedStatement);
        }
        //修改
        List<Element> updateList = rootElement.selectNodes("//update");
        for (Element element : updateList) {
            String id = element.attributeValue("id");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();//sql
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            //xml中的唯一标识  namespace+id
            configuration.getMapper().put(namespace+"."+id,mappedStatement);
        }
    }
}
