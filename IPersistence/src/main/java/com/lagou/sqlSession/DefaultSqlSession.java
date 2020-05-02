package com.lagou.sqlSession;


import com.lagou.pojo.Configuration;

import java.lang.reflect.*;
import java.util.List;

public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    //查询所有
    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        //完成对simpleExecutor中的query方法中的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        List<Object> query = simpleExecutor.query(configuration, configuration.getMapper().get(statementId), params);

        return (List<E>) query;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为多条或者为空");
        }
    }

    @Override
    public void save(String statementId, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        simpleExecutor.save(configuration, configuration.getMapper().get(statementId), params);
    }
    //删除方法
    @Override
    public void delete(String statementId, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        simpleExecutor.delete(configuration, configuration.getMapper().get(statementId), params);
    }
    //修改方法
    @Override
    public void update(String statementId, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        simpleExecutor.update(configuration, configuration.getMapper().get(statementId), params);
    }


    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        //jdk 动态代理 为dao接口生成代理对象 并返回
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //proxy:当前代理对象的应用
                //  method:当前代理对象的引用
                // args传递的参数
                //准备参数 1   statementId sql语句的唯一标识 namespace.标签id的值 ==接口权全限定名称和方法名称
                String methodName = method.getName();//方法名
                String className = method.getDeclaringClass().getName();//全限定名称
                //参数2 params:args
                //获取调用方法的返回值类型
                Type genericReturnType = method.getGenericReturnType();
                if (methodName.indexOf("save") != -1) {//判断是否为保存方法
                    save(className + "." + methodName, args);
                    return  null;
                } else if(methodName.indexOf("delete") != -1){
                    delete(className + "." + methodName, args);
                return null;
                } else if(methodName.indexOf("update") != -1){
                    update(className + "." + methodName, args);
                    return null;
                }else {
                    //判断是否进行了 泛型类型的参数化
                    if (genericReturnType instanceof ParameterizedType) {
                        List<Object> objects = selectList(className + "." + methodName, args);
                        return objects;
                    } else {
                        Object o = selectOne(className + "." + methodName, args);
                        return o;
                    }
                }
            }
        });
        return (T) proxyInstance;
    }

}
