package com.literature.retrieval.controller;

import com.literature.retrieval.vo.AdvancedQueryVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RetrievalControllerTest {

    @Autowired
    private RetrievalController retrievalController;

    @Test
    void getAllLiteratureFromMysql() {
        assertDoesNotThrow(() -> {
            retrievalController.getAllLiteratureFromMysql();
        });
    }

    @Test
    void getAllLiteratureFromEs() {
        assertDoesNotThrow(() -> {
            retrievalController.getAllLiteratureFromEs();
        });
    }

    @Test
    void advancedQueryLiteratureFromMysql() {
        AdvancedQueryVo advancedQueryVo = new AdvancedQueryVo();
        advancedQueryVo.setRetrievalWord("微服务");
        advancedQueryVo.setRetrievalWordType(0);
        assertDoesNotThrow(() -> {
            retrievalController.advancedQueryLiteratureFromMysql(advancedQueryVo);
        });
    }

    @Test
    void advancedQueryLiteratureFromEs() {
        AdvancedQueryVo advancedQueryVo = new AdvancedQueryVo();
        advancedQueryVo.setRetrievalWord("微服务");
        advancedQueryVo.setRetrievalWordType(0);
        assertDoesNotThrow(() -> {
            retrievalController.advancedQueryLiteratureFromEs(advancedQueryVo);
        });
    }

    @Test
    void similarQueryLiteratureFromEs() {
        assertDoesNotThrow(() -> {
            retrievalController.similarQueryLiteratureFromEs("微服务", 1, 5);
        });
    }
}