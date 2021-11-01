package com.literature.retrieval.util;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * 密码工具
 *
 * @PACKAGE_NAME: com.literature.retrieval.util
 * @NAME: MyPasswordEncoder
 * @AUTHOR: Hansel Ma
 * @DATE: 2021/11/1
 * @PROJECT_NAME: literature-retrieval
 */
@Component
public class PasswordEncoder {
    /**
     * 散列+盐值
     *
     * @param rawPassword 密码
     * @return 处理后的密码
     */
    public String encode(CharSequence rawPassword) {
        return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes()));
    }
}