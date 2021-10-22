package com.literature.retrieval.dao.es;

import com.literature.retrieval.po.es.LiteratureEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ES查询接口层
 *
 * @PACKAGE_NAME: com.literature.retrieval.dao.es
 * @NAME: LiteratureMapper
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/10/14
 * @PROJECT_NAME: literature-retrieval
 */
@Repository
public interface LiteratureRepository extends ElasticsearchRepository<LiteratureEs, Integer> {

    List<LiteratureEs> findByTitleLike(String title);

    List<LiteratureEs> findByTitleContaining(String title);
}
