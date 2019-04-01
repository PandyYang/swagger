package com.forezp.helloworld.dao;

import com.forezp.helloworld.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Pandy
 * @Date: 2019/3/30 23:10
 * @Version 1.0
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
