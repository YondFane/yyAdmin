package com.yfan.yyadmin.common.config;

import com.yfan.yyadmin.common.mapper.generator.GeneratorMapper;
import com.yfan.yyadmin.common.mapper.generator.MySQLGeneratorMapper;
import com.yfan.yyadmin.common.mapper.generator.OracleGeneratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 数据源配置类
 * @Author: YFAN
 * @CreateTime: 2022-07-02 16:33
 */
@Configuration
public class DataSourceConfig {

    @Value("${current.datasource}")
    private String database;
    @Autowired
    private MySQLGeneratorMapper mySQLGeneratorMapper;

    @Autowired
    private OracleGeneratorMapper oracleGeneratorMapper;


    @Bean
    @Primary
    public GeneratorMapper getGeneratorDao() {
        // 根据当前数据源获取Mapper
        if ("mysql".equalsIgnoreCase(database)) {
            return mySQLGeneratorMapper;
        } else if ("oracle".equalsIgnoreCase(database)) {
            return oracleGeneratorMapper;
        } else {
            throw new RuntimeException("不支持当前数据库：" + database);
        }
    }
}
