package com.literature.retrieval.po.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 文献实体类
 *
 * @PACKAGE_NAME: com.literature.retrieval.po
 * @NAME: Literature
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/10/14
 * @PROJECT_NAME: literature-retrieval
 */
@Data
@TableName(value = "literature")
public class LiteratureMysql {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "title")
    private String title;

    @TableField(value = "author")
    private String author;

    @TableField(value = "organization")
    private String organization;

    @TableField(value = "keyword")
    private String keyword;

    @TableField(value = "literature_abstract")
    private String literatureAbstract;

    @TableField(value = "origin")
    private String origin;

    @TableField(value = "publication_time")
    private String publicationTime;

    @TableField(value = "literature_type")
    private String literatureType;
}
