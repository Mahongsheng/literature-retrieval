package com.literature.retrieval.po.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Repository;

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
@Document(indexName = "literature", createIndex = false)
@Repository
public class LiteratureEs {

    @Id
    private String id;

    @Field(value = "title", analyzer = "ik", searchAnalyzer = "hh")
    private String title;

    @Field(value = "author")
    private String author;

    @Field(value = "organization")
    private String organization;

    @Field(value = "keyword")
    private String keyword;

    @Field(value = "literature_abstract")
    private String literatureAbstract;

    @Field(value = "origin")
    private String origin;

    @Field(value = "publication_time")
    private String publicationTime;

    @Field(value = "literature_type")
    private String literatureType;
}
