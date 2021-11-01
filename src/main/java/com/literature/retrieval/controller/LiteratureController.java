package com.literature.retrieval.controller;

import com.literature.retrieval.po.mysql.LiteratureMysql;
import com.literature.retrieval.service.LiteratureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文献添加模块
 *
 * @PACKAGE_NAME: com.literature.retrieval.controller
 * @NAME: LiteratureController
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/10/18
 * @PROJECT_NAME: literature-retrieval
 */
@RestController
@Slf4j
public class LiteratureController {

    @Autowired
    private LiteratureService literatureService;

    /**
     * 添加单个文献到数据库
     *
     * @param literatureMysql 文献
     * @return 是否成功
     */
    @PostMapping("single-add-literature")
    public Boolean singleAddLiterature(@RequestBody LiteratureMysql literatureMysql) {
        try {
            return literatureService.singleAddLiterature(literatureMysql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从csv中批量添加文献到数据库
     *
     * @param csvFile csv文件
     * @return 是否成功
     */
    @PostMapping("csv-add-literature")
    public Boolean csvAddLiterature(@RequestParam("csvFile") MultipartFile csvFile) {
        try {
            return literatureService.csvAddLiterature(csvFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
