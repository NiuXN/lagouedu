package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {
    @Test
    public void test01() throws Exception {
        InputStream resourcesAsStrem = Resources.getResourcesAsStrem("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourcesAsStrem);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
//        User user = new User();
//        user.setId(9);
//        user.setUsername("测试修改用户");
//        for (int i = 0; i < 5; i++) {
//            user.setUsername("新增测试" + i);
//            iUserDao.saveUser(user);
//        }
        //新增
//        iUserDao.saveUser(user);
        // 修改
//        iUserDao.updateUser(user);
        //删除
        iUserDao.deleteUser(9);
    }
}
