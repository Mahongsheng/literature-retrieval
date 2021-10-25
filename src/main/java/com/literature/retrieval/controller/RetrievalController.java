package com.literature.retrieval.controller;

import com.literature.retrieval.dao.es.LiteratureRepository;
import com.literature.retrieval.dao.mysql.LiteratureMapper;
import com.literature.retrieval.po.es.LiteratureEs;
import com.literature.retrieval.po.mysql.LiteratureMysql;
import com.literature.retrieval.service.LiteratureService;
import com.literature.retrieval.vo.AdvancedQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文献检索模块
 *
 * @PACKAGE_NAME: com.literature.retrieval.controller
 * @NAME: RetrievalController
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/10/14
 * @PROJECT_NAME: literature-retrieval
 */
@RestController
@Slf4j
public class RetrievalController {

    @Autowired
    private LiteratureRepository literatureRepository;

    @Autowired
    private LiteratureMapper literatureMapper;

    @Autowired
    private LiteratureService literatureService;

    /**
     * 从数据库中获取全部文献
     *
     * @return 文献结果
     * @author mahongsheng
     */
    @GetMapping("/all/mysql")
    public List<LiteratureMysql> getAllLiteratureFromMysql() {
        return literatureMapper.selectList(null);
    }

    /**
     * 从es中获取全部文献
     *
     * @return 文献结果
     * @author mahongsheng
     */
    @GetMapping("/all/es")
    public Iterable<LiteratureEs> getAllLiteratureFromEs() {
        return literatureRepository.findAll();
    }

    /**
     * 在MySQL中进行高级检索
     *
     * @param advancedQueryVo 检索条件
     * @return 文献结果
     * @author mahongsheng
     */
    @PostMapping("/mysql/advanced-query")
    public List<LiteratureMysql> advancedQueryLiteratureFromMysql(@RequestBody AdvancedQueryVo advancedQueryVo) {
        try {
            return literatureService.advancedQueryLiteratureFromMysql(advancedQueryVo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 在Elasticsearch中进行高级检索
     *
     * @param advancedQueryVo 检索条件
     * @return 文献结果
     * @author mahongsheng
     */
    @PostMapping("/es/advanced-query")
    public List<LiteratureEs> advancedQueryLiteratureFromEs(@RequestBody AdvancedQueryVo advancedQueryVo) {
        try {
            return literatureService.advancedQueryLiteratureFromEs(advancedQueryVo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
