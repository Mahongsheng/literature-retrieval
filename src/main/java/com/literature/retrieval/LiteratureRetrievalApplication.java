package com.literature.retrieval;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.literature.retrieval.dao.mysql")
public class LiteratureRetrievalApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiteratureRetrievalApplication.class, args);
    }

}
