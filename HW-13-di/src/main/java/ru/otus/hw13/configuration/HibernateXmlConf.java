package ru.otus.hw13.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class HibernateXmlConf {
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        var sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
        sessionFactory.setPackagesToScan("ru.otus.hw13");

        return sessionFactory;
    }
}