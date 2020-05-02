package com.lagou.dao;

import com.lagou.pojo.User;

import java.util.List;

public interface IUserDao {
    //查询所有用户
    public List<User> findAll() throws Exception;
    //条件查询
    public  User queryUser(User user) throws Exception;
    //新增用户
    public void saveUser(User user) throws  Exception;
    //删除
    public void deleteUser(Integer id) throws  Exception;
    //修改
    public void updateUser(User user) throws  Exception;

}
