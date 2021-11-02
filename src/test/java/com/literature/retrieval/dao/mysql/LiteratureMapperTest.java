package com.literature.retrieval.dao.mysql;

import com.literature.retrieval.po.mysql.LiteratureMysql;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @PACKAGE_NAME: com.literature.retrieval.dao.mysql
 * @NAME: LiteratureMapperTest
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/10/14
 * @PROJECT_NAME: literature-retrieval
 */
@SpringBootTest
public class LiteratureMapperTest {

    @Autowired
    private LiteratureMapper literatureMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<LiteratureMysql> literatureMysqlList = literatureMapper.selectList(null);
        literatureMysqlList.forEach(System.out::println);
    }
}
