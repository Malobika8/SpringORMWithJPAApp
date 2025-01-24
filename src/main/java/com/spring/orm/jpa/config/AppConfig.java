package com.spring.orm.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3333/DB1");
        dataSource.setUsername("root");
        dataSource.setPassword("password");

        return dataSource;
    }

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "create");

        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        managerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        managerFactoryBean.setPackagesToScan("com.spring.orm.jpa.entity");
        managerFactoryBean.setDataSource(dataSource());
        managerFactoryBean.setJpaProperties(jpaProperties());
        managerFactoryBean.setPersistenceUnitName("test-unit");

        return managerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
        return transactionManager;
    }
}
