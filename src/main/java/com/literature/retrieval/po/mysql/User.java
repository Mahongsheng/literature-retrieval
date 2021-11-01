package com.literature.retrieval.po.mysql;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * 用户实体类
 *
 * @PACKAGE_NAME: com.literature.retrieval.po.mysql
 * @NAME: User
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/11/1
 * @PROJECT_NAME: literature-retrieval
 */
@Data
@TableName(value = "user")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;
}
