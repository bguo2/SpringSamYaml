package com.example.springyamlpropsam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
public class ConfigDbProperties {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.testdb1")
    public DataSource testDb1Datasource()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate testDb1JdbcTemplate()
    {
        DataSource ds = testDb1Datasource();
        return new JdbcTemplate(ds);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.testdb2")
    public DataSource testDb2Datasource()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate testDb2JdbcTemplate()
    {
        DataSource ds = testDb2Datasource();
        return new JdbcTemplate(ds);
    }
}
