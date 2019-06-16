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
    @ConfigurationProperties(prefix = "spring.datasource.cspdb")
    public DataSource cspDbDatasource()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate cspDbJdbcTemplate()
    {
        DataSource ds = cspDbDatasource();
        return new JdbcTemplate(ds);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.pandb")
    public DataSource panDbDbDatasource()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate pandbDbJdbcTemplate()
    {
        DataSource ds = panDbDbDatasource();
        return new JdbcTemplate(ds);
    }
}
