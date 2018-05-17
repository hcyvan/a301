package com.navy.b.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "bEntityManagerFactory",
        transactionManagerRef = "bTransactionManager",
        basePackages = "com.navy.b.repository"
)
public class BDataSourceConfig {
    @Primary
    @Bean(name = "bDataSource")
    @ConfigurationProperties(prefix="dbb.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("bEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean bEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("bDataSource") DataSource dataSource
    ) {
        HashMap<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class);
        props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class);
        return builder
                .dataSource(dataSource)
                .packages("com.navy.b.model")
                .persistenceUnit("dbbPersistenceUnit")
                .properties(props)
                .build();
    }

    @Primary
    @Bean(name = "bTransactionManager")
    public PlatformTransactionManager bTransactionManager(@Qualifier("bEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
