package com.literature.retrieval.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.literature.retrieval.dao.es.LiteratureRepository;
import com.literature.retrieval.dao.mysql.LiteratureMapper;
import com.literature.retrieval.po.es.LiteratureEs;
import com.literature.retrieval.po.mysql.LiteratureMysql;
import com.literature.retrieval.service.LiteratureService;
import com.literature.retrieval.vo.AdvancedQueryVo;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 检索模块实现层
 *
 * @PACKAGE_NAME: com.literature.retrieval.service.impl
 * @NAME: LiteratureServiceImpl
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/10/18
 * @PROJECT_NAME: literature-retrieval
 */
@Service
public class LiteratureServiceImpl implements LiteratureService {
    @Autowired
    private LiteratureMapper literatureMapper;

    @Autowired
    private LiteratureRepository literatureRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<LiteratureMysql> advancedQueryLiteratureFromMysql(AdvancedQueryVo advancedQueryVo) {
        QueryWrapper<LiteratureMysql> wrapper = new QueryWrapper<>();
        /*
        主题部分
         */
        if (advancedQueryVo.getRetrievalWord() != null
                && !advancedQueryVo.getRetrievalWord().isEmpty()) {
            handleTitle(wrapper, advancedQueryVo.getRetrievalWord(), advancedQueryVo.getRetrievalWordType());
        }
        /*
        作者部分
         */
        if (advancedQueryVo.getAuthor() != null
                && !advancedQueryVo.getAuthor().isEmpty()) {
            wrapper.like("author", advancedQueryVo.getAuthor());
        }
        /*
        单位部分
         */
        if (advancedQueryVo.getOrganization() != null
                && !advancedQueryVo.getOrganization().isEmpty()) {
            wrapper.like("organization", advancedQueryVo.getOrganization());
        }
        /*
        来源部分
         */
        if (advancedQueryVo.getOrigin() != null
                && !advancedQueryVo.getOrigin().isEmpty()) {
            wrapper.like("origin", advancedQueryVo.getOrigin());
        }
        /*
        发表时间部分
        */
        if (advancedQueryVo.getPublicationTime() != null
                && !advancedQueryVo.getPublicationTime().isEmpty()) {
            // TODO
        }
        /*
        文献类型部分
         */
        if (advancedQueryVo.getRetrievalWordType() != null
                && !advancedQueryVo.getLiteratureType().isEmpty()) {
            wrapper.eq("literature_type", advancedQueryVo.getLiteratureType());
        }
        return literatureMapper.selectList(wrapper);
    }

    /**
     * 处理检索词并添加检索条件给wrapper
     *
     * @param wrapper           传入的数据库wrapper
     * @param retrievalWord     检索词
     * @param retrievalWordType 检索方式
     */
    private void handleTitle(QueryWrapper<LiteratureMysql> wrapper,
                             String retrievalWord,
                             Integer retrievalWordType) {
        String[] retrievalWordList = retrievalWord.split(",");
        List<String> formattedRetrievalWordList;
        switch (retrievalWordType) {
            case 0:
                // 包含全部检索词
                formattedRetrievalWordList = processRetrievalWord(retrievalWordList);
                formattedRetrievalWordList.forEach(formattedRetrievalWord -> {
                    wrapper.like("title", formattedRetrievalWord);
                });
                break;
            case 1:
                // 包含全部精确检索词，即不会进行分词处理
                formattedRetrievalWordList = Arrays.asList(retrievalWordList);
                formattedRetrievalWordList.forEach(formattedRetrievalWord -> {
                    wrapper.like("title", formattedRetrievalWord);
                });
                break;
            case 2:
                // 包含至少一个检索词
                formattedRetrievalWordList = processRetrievalWord(retrievalWordList);
                for (int i = 0; i < formattedRetrievalWordList.size(); i++) {
                    wrapper.like("title", formattedRetrievalWordList.get(i));
                    if (i != formattedRetrievalWordList.size() - 1) {
                        wrapper.or();
                    }
                }
                break;
            case 3:
                // 不包含检索词
                formattedRetrievalWordList = processRetrievalWord(retrievalWordList);
                formattedRetrievalWordList.forEach(formattedRetrievalWord -> {
                    wrapper.notLike("title", formattedRetrievalWord);
                });
                break;
            default:
        }
    }

    /**
     * 对中文短语进行分词处理，并去除停用词
     *
     * @param retrievalWordList 中文短语列表
     * @return 分词且去除停用词后的词语列表
     */
    private List<String> processRetrievalWord(String[] retrievalWordList) {
        List<String> formattedRetrievalWordList = new ArrayList<>();
        StopRecognition filter = new StopRecognition();
        filter.insertStopNatures("c");
        filter.insertStopNatures("p");

        for (String retrievalWord : retrievalWordList) {
            String formattedRetrievalWord = ToAnalysis
                    .parse(retrievalWord)
                    .recognition(filter)
                    .toStringWithOutNature();
            formattedRetrievalWordList.addAll(Arrays.asList(formattedRetrievalWord.split(",")));
        }
        return formattedRetrievalWordList;
    }

    @Override
    public List<LiteratureEs> advancedQueryLiteratureFromEs(AdvancedQueryVo advancedQueryVo) {
        Criteria baseCriteria = null;
        /*
        主题部分
         */
        if (advancedQueryVo.getRetrievalWord() != null
                && !advancedQueryVo.getRetrievalWord().isEmpty()) {
            baseCriteria = new Criteria("title");
            handleEsTitle(baseCriteria, advancedQueryVo.getRetrievalWord(), advancedQueryVo.getRetrievalWordType());
        }
        /*
        作者部分
         */
        if (advancedQueryVo.getAuthor() != null
                && !advancedQueryVo.getAuthor().isEmpty()) {
            if (baseCriteria == null) {
                baseCriteria = new Criteria("author");
            } else {
                baseCriteria.and("author");
            }
            baseCriteria.matches(advancedQueryVo.getAuthor());
        }
        /*
        单位部分
         */
        if (advancedQueryVo.getOrganization() != null
                && !advancedQueryVo.getOrganization().isEmpty()) {
            if (baseCriteria == null) {
                baseCriteria = new Criteria("organization");
            } else {
                baseCriteria.and("organization");
            }
            baseCriteria.matches(advancedQueryVo.getOrganization());
        }
        /*
        来源部分
         */
        if (advancedQueryVo.getOrigin() != null
                && !advancedQueryVo.getOrigin().isEmpty()) {
            if (baseCriteria == null) {
                baseCriteria = new Criteria("origin");
            } else {
                baseCriteria.and("origin");
            }
            baseCriteria.matches(advancedQueryVo.getOrigin());
        }
        /*
        发表时间部分
        */
        if (advancedQueryVo.getPublicationTime() != null
                && !advancedQueryVo.getPublicationTime().isEmpty()) {
            // TODO
        }
        /*
        文献类型部分
         */
        if (advancedQueryVo.getRetrievalWordType() != null
                && !advancedQueryVo.getLiteratureType().isEmpty()) {
            if (baseCriteria == null) {
                baseCriteria = new Criteria("origin");
            } else {
                baseCriteria.and("literature_type");
            }
            baseCriteria.matches(advancedQueryVo.getLiteratureType());
        }

        if (baseCriteria == null) {
            return new ArrayList<>();
        }

        CriteriaQuery criteriaQuery = new CriteriaQuery(baseCriteria);
        SearchHits<LiteratureEs> search = elasticsearchOperations.search(criteriaQuery, LiteratureEs.class);
        return search.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    private void handleEsTitle(Criteria baseCriteria,
                               String retrievalWord,
                               Integer retrievalWordType) {
        List<String> formattedRetrievalWordList;
        switch (retrievalWordType) {
            case 0:
                // 包含全部检索词
                baseCriteria.matches(retrievalWord);
                break;
            case 1:
                // 包含全部精确检索词，即不会进行分词处理
                formattedRetrievalWordList = Arrays.asList(retrievalWord.split(","));
                formattedRetrievalWordList.forEach(baseCriteria::matchesAll);
                break;
            case 2:
                // 包含至少一个检索词
                formattedRetrievalWordList = Arrays.asList(retrievalWord.split(","));
                for (int i = 0; i < formattedRetrievalWordList.size(); i++) {
                    baseCriteria.matches(formattedRetrievalWordList.get(i));
                    if (i != formattedRetrievalWordList.size() - 1) {
                        baseCriteria.or("title");
                    }
                }
                break;
            case 3:
                // 不包含检索词
                baseCriteria.not().matches(retrievalWord);
                break;
            default:
        }
    }
}
