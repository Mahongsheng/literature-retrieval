package com.literature.retrieval.service.impl;

import com.literature.retrieval.dao.mysql.LiteratureMapper;
import com.literature.retrieval.po.mysql.LiteratureMysql;
import com.literature.retrieval.service.LiteratureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @PACKAGE_NAME: com.literature.retrieval.service.impl
 * @NAME: LiteratureServiceImpl
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/10/18
 * @PROJECT_NAME: literature-retrieval
 */
@Service
public class LiteratureServiceImpl implements LiteratureService {

    @Autowired
    private LiteratureMapper literatureMapper;

    @Override
    public boolean singleAddLiterature(LiteratureMysql literatureMysql) {
        int insert = literatureMapper.insert(literatureMysql);
        return insert == 1;
    }
}
