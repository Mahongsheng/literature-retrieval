package com.literature.retrieval.controller;

import com.literature.retrieval.po.mysql.LiteratureMysql;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LiteratureControllerTest {

    @Autowired
    private LiteratureController literatureController;

    @Test
    void singleAddLiterature() {
        LiteratureMysql literatureMysql = new LiteratureMysql();
        literatureMysql.setTitle("单元测试");
        literatureMysql.setAuthor("马洪升");
        literatureMysql.setOrganization("北理工");
        literatureMysql.setKeyword("单元测试");
        literatureMysql.setLiteratureAbstract("单元测试");
        literatureMysql.setOrigin("单元测试");
        literatureMysql.setPublicationTime("2021-11-6");
        literatureMysql.setLiteratureType("期刊");
        assertTrue(literatureController.singleAddLiterature(literatureMysql));
    }

    @Test
    void csvAddLiterature() {
        System.out.println("该测试使用集成测试");
    }
}