package com.literature.retrieval.service.impl;

import com.csvreader.CsvReader;
import com.literature.retrieval.dao.mysql.LiteratureMapper;
import com.literature.retrieval.po.mysql.LiteratureMysql;
import com.literature.retrieval.service.LiteratureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

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
    public boolean singleAddLiterature(LiteratureMysql literatureMysql) {
        int insert = literatureMapper.insert(literatureMysql);
        return insert == 1;
    }

    @Override
    public boolean csvAddLiterature(MultipartFile csvFile) throws IOException {
        String filePath = UUID.randomUUID() + "+" + csvFile.getOriginalFilename();
        File file = new File(filePath);
        file.createNewFile();
        csvFile.transferTo(file);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        CsvReader csvReader = new CsvReader(bufferedReader);
        System.out.println(csvReader.getRawRecord());
        return false;
    }
}
