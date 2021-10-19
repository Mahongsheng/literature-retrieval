package com.literature.retrieval.service;

import com.literature.retrieval.vo.AdvancedQueryVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LiteratureServiceTest {

    @Autowired
    private LiteratureService literatureService;

    @Test
    void advancedQueryLiteratureFromMysql() {
        AdvancedQueryVo advancedQueryVo = new AdvancedQueryVo();
        advancedQueryVo.setRetrievalWord("软件测试与微服务,大数据");
        advancedQueryVo.setRetrievalWordType(3);
        literatureService.advancedQueryLiteratureFromMysql(advancedQueryVo).forEach(System.out::println);
    }
}