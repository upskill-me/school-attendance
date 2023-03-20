package me.upskill.schoolattendance.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Data sources, entities, JPA configuration
 * HikariCP is used as connection pool implementation
 */
@Configuration
@EnableJpaRepositories(basePackages = "me.upskill.schoolattendance.repository")
@EnableTransactionManagement
@EntityScan(basePackages = "me.upskill.schoolattendance.api.entities.jpa")
@EnableJpaAuditing
public class DatasourceConfig {

    /**
     * private static class level logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DatasourceConfig.class);

    /**
     * The name of the property to access the database name
     */
    private static final String DATABASE_NAME_PROPERTY = "me.upskill.schoolattendance.data-source.databaseName";

    /**
     * Return a custom configured {@link HikariDataSource} instance
     *
     * @return {@link HikariDataSource} implementation
     */
    @Bean
    @ConfigurationProperties("me.upskill.schoolattendance.data-source")
    public HikariDataSource dataSource(Environment environment) {
        HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder.create().type(HikariDataSource.class).build();
        // NOTE: Do we have to do this ? Find out how to configure this from properties file
        final String databaseName = environment.getProperty(DATABASE_NAME_PROPERTY);
        if (StringUtils.isBlank(databaseName)) {
            throw new IllegalStateException("databaseName cannot be empty/null");
        }
        LOGGER.info("initializing custom primary data source with database name {}", databaseName);
        dataSource.addDataSourceProperty("databaseName", databaseName);
        return dataSource;
    }
}
