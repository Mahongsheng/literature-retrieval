package com.literature.retrieval.service.impl;

import com.csvreader.CsvReader;
import com.literature.retrieval.dao.mysql.LiteratureMapper;
import com.literature.retrieval.po.mysql.LiteratureMysql;
import com.literature.retrieval.service.LiteratureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 文献检索模块实现层
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

    @Override
    public boolean singleAddLiterature(LiteratureMysql literatureMysql) {
        int insert = literatureMapper.insert(literatureMysql);
        return insert == 1;
    }

    @Override
    @Transactional
    public boolean csvAddLiterature(MultipartFile csvFile) throws Exception {
        CsvReader csvReader = new CsvReader(csvFile.getInputStream(), StandardCharsets.UTF_8);
        csvReader.readHeaders();
        List<List<String>> csvContents = new ArrayList<>();
        while (csvReader.readRecord()) {
            List<String> csvRow = new ArrayList<>();
            for (int i = 1; i < 9; i++) {
                csvRow.add(csvReader.get(i));
            }
            csvContents.add(csvRow);
        }

        for (List<String> row : csvContents) {
            LiteratureMysql literatureMysql = new LiteratureMysql();
            literatureMysql.setTitle(row.get(0));
            literatureMysql.setAuthor(row.get(1));
            literatureMysql.setOrganization(row.get(2));
            literatureMysql.setKeyword(row.get(3));
            literatureMysql.setLiteratureAbstract(row.get(4));
            literatureMysql.setOrigin(row.get(5));
            literatureMysql.setPublicationTime(row.get(6));
            literatureMysql.setLiteratureType(row.get(7));
            if (literatureMapper.insert(literatureMysql) != 1) {
                throw new Exception("插入失败");
            }
        }
        return true;
    }
}
