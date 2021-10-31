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
    boolean singleAddLiterature(LiteratureMysql literatureMysql);
}
