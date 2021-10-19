package com.literature.retrieval.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.literature.retrieval.dao.mysql.LiteratureMapper;
import com.literature.retrieval.po.mysql.LiteratureMysql;
import com.literature.retrieval.service.LiteratureService;
import com.literature.retrieval.vo.AdvancedQueryVo;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
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

    @Override
    public List<LiteratureMysql> advancedQueryLiteratureFromMysql(AdvancedQueryVo advancedQueryVo) {
        String[] retrievalWordList = advancedQueryVo.getRetrievalWord().split(",");
        QueryWrapper<LiteratureMysql> wrapper = new QueryWrapper<>();
        switch (advancedQueryVo.getRetrievalWordType()) {
            case 0:
                // 包含全部检索词

                break;
            case 1:
                // 包含精确检索词，即不会进行分词处理
                break;
            case 2:
                // 包含至少一个检索词
                break;
            case 3:
                // 不包含检索词
                List<String> formattedRetrievalWordList = new ArrayList<>();
                for (String retrievalWord : retrievalWordList) {
                    // 去除停用词
                    StopRecognition filter = new StopRecognition();
                    filter.insertStopNatures("c");
                    filter.insertStopNatures("p");
                    String formattedRetrievalWord = ToAnalysis
                            .parse(retrievalWord)
                            .recognition(filter)
                            .toString();
                    formattedRetrievalWordList.addAll(Arrays.asList(formattedRetrievalWord.split(",")));
                }
                formattedRetrievalWordList.forEach(formattedRetrievalWord -> {
                    wrapper.notLike("title", formattedRetrievalWord);
                });
                return literatureMapper.selectList(wrapper);
            default:
        }
        return null;
    }
}
