package com.forezp.helloworld.service;

import com.forezp.helloworld.dao.UserDao;
import com.forezp.helloworld.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/3/30 23:11
 * @Version 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    public User findUserByUsername(String username){
        return userDao.findByUsername(username);
    }
    public List<User> findAll(){
        return userDao.findAll();
    }
    public User saveUser(User user){
        return userDao.save(user);
    }
    public User findUserById(long id){
        return userDao.findById(id).orElse(null);
        //return fidOne(id);只能在低版本中使用 高版本是上面的用法
    }
    public User updateUser(User user){
        return userDao.saveAndFlush(user);
    }
    public void deleteUser(long id){
         userDao.deleteById(id);
         //也可以delete(user); 书上写的是错的~~~
    }
}
