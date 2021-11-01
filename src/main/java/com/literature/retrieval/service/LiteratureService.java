package com.literature.retrieval.service;

import com.literature.retrieval.po.mysql.LiteratureMysql;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文献检索服务接口
 *
 * @PACKAGE_NAME: com.literature.retrieval.service
 * @NAME: LiteratureService
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/10/18
 * @PROJECT_NAME: literature-retrieval
 */
public interface LiteratureService {

    /**
     * 添加单个文献到数据库
     *
     * @param literatureMysql 文献
     * @return 是否成功
     */
    boolean singleAddLiterature(LiteratureMysql literatureMysql);

    /**
     * 从csv中批量添加文献到数据库
     *
     * @param csvFile csv文件
     * @return 是否成功
     */
    boolean csvAddLiterature(MultipartFile csvFile) throws IOException;
}
