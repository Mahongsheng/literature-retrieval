package com.literature.retrieval.po.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户收藏的文献实体类
 *
 * @PACKAGE_NAME: com.literature.retrieval.po.mysql
 * @NAME: UserLiteratureCollection
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/11/1
 * @PROJECT_NAME: literature-retrieval
 */
@Data
@TableName(value = "user_literature_collection")
public class UserLiteratureCollection {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "literature_id")
    private Long literatureId;
}
