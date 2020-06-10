package com.achanzhang.mydatabase.achandatabase.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据源配置
 */
@Configuration
public class DataSourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource defaultDataSource() {
        System.out.println("-------------------- 数据库连接 init ---------------------");
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

}