package com.literature.retrieval.po;

import lombok.Data;

/**
 * @PACKAGE_NAME: com.literature.retrieval.po
 * @NAME: Literature
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/10/14
 * @PROJECT_NAME: literature-retrieval
 */
@Data
public class Literature {

    private Integer id;

    private String title;

    private String author;

    private String organization;

    private String keyword;

    private String _abstract;

    private String origin;

    private String publicationTime;

    private String literatureType;
}
