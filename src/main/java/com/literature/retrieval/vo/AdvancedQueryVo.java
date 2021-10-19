package com.literature.retrieval.vo;

import lombok.Data;

/**
 * 高级检索传参
 *
 * @PACKAGE_NAME: com.literature.retrieval.vo
 * @NAME: AdvancedQueryVo
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/10/19
 * @PROJECT_NAME: literature-retrieval
 */
@Data
public class AdvancedQueryVo {
    /**
     * 检索词，多个检索词以逗号分隔
     */
    private String retrievalWord;

    /**
     * 检索词状态：0为包含全部检索词、1为包含精确检索词、2为包含至少一个检索词、3为不包含检索词
     */
    private Integer retrievalWordType;

    /**
     * 作者
     */
    private String author;

    /**
     * 机构
     */
    private String organization;

    /**
     * 出版物
     */
    private String origin;

    /**
     * 发表时间
     */
    private String publicationTime;

    /**
     * 出版物类型
     */
    private String literatureType;

}
