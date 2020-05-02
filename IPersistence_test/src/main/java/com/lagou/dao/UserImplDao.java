//package com.lagou.dao;
//
//import com.lagou.io.Resources;
//import com.lagou.pojo.User;
//import com.lagou.sqlSession.SqlSession;
//import com.lagou.sqlSession.SqlSessionFactory;
//import com.lagou.sqlSession.SqlSessionFactoryBuilder;
//
//import java.io.InputStream;
//import java.util.List;
//
//public class UserImplDao implements IUserDao {
//    @Override
//    public List<User> findAll() throws Exception {
//        InputStream resourcesAsStrem = Resources.getResourcesAsStrem("SqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourcesAsStrem);
//        //sqlsession
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        User user = new User();
//        user.setId(1);
//        user.setUsername("lucy");
//        List<User> users = sqlSession.selectList("user.selectList");
//        for (int i = 0; i < users.size(); i++) {
//            System.out.println(users.get(i));
//        }
//        return null;
//    }
//
//    @Override
//    public User queryUser(User user) throws Exception {
//        InputStream resourcesAsStrem = Resources.getResourcesAsStrem("SqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourcesAsStrem);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        User users = sqlSession.selectOne("user.selectList",user);
//        return  users;
//    }
//}
