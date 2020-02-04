package ru.otus.hw13;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.otus.hw13.configuration.HibernateXmlConf;
import ru.otus.hw13.configuration.WebConfig;

@ComponentScan
@Import({HibernateXmlConf.class,
        WebConfig.class})
public class AppConfig {
}
