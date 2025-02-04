package com.wtchuigo.reunion.config.db;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class LiquiaseConfiguration {

    @Bean
    public SpringLiquibase liquibaseAddresses(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog/masterdata/addresses.xml");

        return liquibase;
    }
    
    @Bean
    @DependsOn("liquibaseAddresses")
    public SpringLiquibase liquibaseMembers(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog/masterdata/members.xml");

        return liquibase;
    }
}
