package com.literature.retrieval;

import org.ansj.splitWord.analysis.ToAnalysis;
import org.junit.jupiter.api.Test;

/**
 * 测试分词
 *
 * @PACKAGE_NAME: com.literature.retrieval
 * @NAME: AnsjTest
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/10/20
 * @PROJECT_NAME: literature-retrieval
 */
class AnsjTest {

    @Test
    void ansjTest() {
        System.out.println(ToAnalysis.parse("微服务").toString());
    }
}
