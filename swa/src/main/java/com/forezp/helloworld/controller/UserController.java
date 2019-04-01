package com.forezp.helloworld.controller;

import com.forezp.helloworld.pojo.User;
import com.forezp.helloworld.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/3/30 23:12
 * @Version 1.0
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/${username}")
    public User getUser(@PathVariable("username")String username){
        return userService.findUserByUsername(username);
    }

    @ApiOperation(value="用户列表",notes = "用户列表")
    @RequestMapping(value = {""},method = RequestMethod.GET)
    public List<User> getUsers(){
        List<User> users = userService.findAll();
        return users;
    }

    @ApiOperation(value = "创建用户",notes = "创建用户")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public User postUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @ApiOperation(value = "获取用户的详细信息",notes = "根据url的id来获取详细的信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @ApiOperation(value = "更新信息",notes = "根据url的id来指定更新用户信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public User putUser(@PathVariable Long id,@RequestBody User user){
        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setId(user.getId());
        return userService.updateUser(u);
    }

    @ApiOperation(value = "删除用户",notes = "根据url的id来指定删除的用户")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return "success";
    }

    @ApiIgnore
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String jsonTest(){
        return "hello world";
    }
}
