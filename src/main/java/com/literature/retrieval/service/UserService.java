package com.literature.retrieval.service;

import com.literature.retrieval.po.mysql.User;

/**
 * 用户模块接口层
 *
 * @PACKAGE_NAME: com.literature.retrieval.service
 * @NAME: UserService
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/11/1
 * @PROJECT_NAME: literature-retrieval
 */
public interface UserService {

    boolean registerNewUser(User user);
}
