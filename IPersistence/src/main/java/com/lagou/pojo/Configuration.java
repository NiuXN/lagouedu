package com.lagou.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
//核心配置类
//对sqlMapConfig.xml进行解析
public class Configuration {
    //数据源对象
    private DataSource dataSource;

    // map中的key 为 statementId value:封装好的MappedStatement对象
    private Map<String,MappedStatement> mapper= new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMapper() {
        return mapper;
    }

    public void setMapper(Map<String, MappedStatement> mapper) {
        this.mapper = mapper;
    }
}
