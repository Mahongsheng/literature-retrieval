package com.literature.retrieval.controller;

import com.literature.retrieval.po.mysql.User;
import com.literature.retrieval.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理模块
 *
 * @PACKAGE_NAME: com.literature.retrieval.controller
 * @NAME: UserController
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/11/1
 * @PROJECT_NAME: literature-retrieval
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param user 用户名和密码
     * @return 是否成功
     */
    @PostMapping("register-new-user")
    public Boolean registerNewUser(@RequestBody User user) {
        try {
            return userService.registerNewUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用户登录
     *
     * @param user 用户名和密码
     * @return 是否成功
     */
    @PostMapping("login")
    public Boolean login(@RequestBody User user) {
        try {
            return userService.login(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
