package com.literature.retrieval.service;

import com.literature.retrieval.po.es.LiteratureEs;
import com.literature.retrieval.po.mysql.LiteratureMysql;
import com.literature.retrieval.vo.AdvancedQueryVo;

import java.util.List;

/**
 * 文献检索服务接口
 *
 * @PACKAGE_NAME: com.literature.retrieval.service
 * @NAME: LiteratureService
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/10/18
 * @PROJECT_NAME: literature-retrieval
 */
public interface LiteratureService {

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
}
