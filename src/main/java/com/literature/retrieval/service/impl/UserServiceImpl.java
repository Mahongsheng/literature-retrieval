package com.literature.retrieval.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.literature.retrieval.dao.mysql.UserMapper;
import com.literature.retrieval.po.mysql.User;
import com.literature.retrieval.service.UserService;
import com.literature.retrieval.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户模块实现层
 *
 * @PACKAGE_NAME: com.literature.retrieval.service.impl
 * @NAME: UserServiceImpl
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/11/1
 * @PROJECT_NAME: literature-retrieval
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean registerNewUser(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return userMapper.insert(user) == 1;
    }

    @Override
    public boolean login(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername())
                .eq("password", encodePassword);
        return userMapper.selectOne(queryWrapper) != null;
    }
}
