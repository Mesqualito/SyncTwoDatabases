package com.eigenbaumarkt.sync2datasources.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class WebConfig {

    @Bean(name = "pg_db")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSourcePg() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcTemplatePg")
    public JdbcTemplate jdbcTemplatePg(@Qualifier("pg_db") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean(name = "ms_db")
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSource dataSourceMs() {
        return  DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcTemplateMs")
    public JdbcTemplate jdbcTemplateMs(@Qualifier("ms_db") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
