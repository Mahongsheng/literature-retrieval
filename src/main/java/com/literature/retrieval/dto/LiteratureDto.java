package com.literature.retrieval.dto;

import com.literature.retrieval.po.es.LiteratureEs;
import lombok.Data;

import java.util.List;

/**
 * @PACKAGE_NAME: com.literature.retrieval.dto
 * @NAME: LiteratureDto
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/11/6
 * @PROJECT_NAME: literature-retrieval
 */
@Data
public class LiteratureDto {

    /*
    文献列表
     */
    List<LiteratureEs> literatureEs;

    /*
    总数量
     */
    Long wholeHits;
}
