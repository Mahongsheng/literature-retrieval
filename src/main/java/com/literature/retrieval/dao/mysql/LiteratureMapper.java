package com.literature.retrieval.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.literature.retrieval.po.mysql.LiteratureMysql;
import org.springframework.stereotype.Repository;

/**
 * 数据库文献模块查询接口层
 *
 * @PACKAGE_NAME: com.literature.retrieval.dao.mysql
 * @NAME: LiteratureMapper
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/10/14
 * @PROJECT_NAME: literature-retrieval
 */
@Repository
public interface LiteratureMapper extends BaseMapper<LiteratureMysql> {
}
