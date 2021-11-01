package com.literature.retrieval.service;

import com.literature.retrieval.po.mysql.LiteratureMysql;
import com.literature.retrieval.po.mysql.User;

import java.util.List;

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

    /**
     * 用户注册
     *
     * @param user 用户名和密码
     * @return 是否成功
     */
    boolean registerNewUser(User user);

    /**
     * 用户登录
     *
     * @param user 用户名和密码
     * @return 是否成功
     */
    boolean login(User user);

    /**
     * 收藏文献
     *
     * @param userId       用户ID
     * @param literatureId 文献ID
     * @return 是否成功
     */
    Boolean userLiteratureCollection(Integer userId, Long literatureId);

    /**
     * 查询用户收藏
     *
     * @param userId 用户ID
     * @return 文献列表
     */
    List<LiteratureMysql> queryUserCollection(Integer userId);
}
