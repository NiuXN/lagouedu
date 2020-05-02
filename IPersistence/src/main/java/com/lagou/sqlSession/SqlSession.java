package com.lagou.sqlSession;

import java.util.List;

public interface SqlSession {
    //查询所有
    public <E> List<E> selectList(String statementId, Object... params) throws Exception;

    //条件查询
    public <T> T selectOne(String statementId, Object... params) throws Exception;

    // 未dao接口生成代理实现类
    public <T> T getMapper(Class<T> mapperClass);

    //保存方法
    public void save(String statementId, Object... params) throws Exception;
    //删除方法
    public void delete(String statementId, Object... params) throws Exception;
    //修改方法
    public void update(String statementId, Object... params) throws Exception;
}
