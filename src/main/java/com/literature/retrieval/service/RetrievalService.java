package com.literature.retrieval.service;

import com.literature.retrieval.po.es.LiteratureEs;
import com.literature.retrieval.po.mysql.LiteratureMysql;
import com.literature.retrieval.vo.AdvancedQueryVo;

import java.util.List;

/**
 * 文献检索服务接口
 *
 * @PACKAGE_NAME: com.literature.retrieval.service
 * @NAME: RetrievalService
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/10/18
 * @PROJECT_NAME: literature-retrieval
 */
public interface RetrievalService {


    /**
     * 在MySQL中进行高级检索
     *
     * @param advancedQueryVo 检索条件
     * @return 文献结果
     * @author mahongsheng
     */
    List<LiteratureMysql> advancedQueryLiteratureFromMysql(AdvancedQueryVo advancedQueryVo);

    /**
     * 在Elasticsearch中进行高级检索
     *
     * @param advancedQueryVo 检索条件
     * @return 文献结果
     * @author mahongsheng
     */
    List<LiteratureEs> advancedQueryLiteratureFromEs(AdvancedQueryVo advancedQueryVo);

    /**
     * 相似文献查询
     *
     * @param originKeywords 文献关键词
     * @return 文献结果
     */
    List<LiteratureEs> similarQueryLiteratureFromEs(String originKeywords, int page, int size);
}
