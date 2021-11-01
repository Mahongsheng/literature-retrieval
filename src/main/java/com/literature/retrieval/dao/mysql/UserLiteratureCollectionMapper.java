package com.literature.retrieval.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.literature.retrieval.po.mysql.UserLiteratureCollection;
import org.springframework.stereotype.Repository;

/**
 * 收藏实体接口层
 *
 * @PACKAGE_NAME: com.literature.retrieval.dao.mysql
 * @NAME: UserLiteratureCollectionMapper
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/11/1
 * @PROJECT_NAME: literature-retrieval
 */
@Repository
public interface UserLiteratureCollectionMapper extends BaseMapper<UserLiteratureCollection> {
}
