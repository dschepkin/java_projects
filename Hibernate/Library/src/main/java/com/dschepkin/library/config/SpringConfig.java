package com.dschepkin.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.Properties;

@Configuration //это говорит, о том что это тоже Bean и его надо тоже загрузить в applicationContext
@ComponentScan("com.dschepkin.library") //указываем где искать кандидатов в Bean/s
@PropertySource("classpath:hibernate.properties") //путь до файла конфигурации. Для sessionFactory, EntityManager
@EnableTransactionManagement //доверяем Spring управлять транзакциями, т.е теперь мы не будет открывать и закрывать их
public class SpringConfig {

    private final ApplicationContext applicationContext;
    private final Environment environment; //этот класс позволит подгрузить данные из hibernate.properties

    @Autowired
    public SpringConfig(ApplicationContext applicationContext, Environment environment) {
        this.applicationContext = applicationContext;
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty("hibernate.driver_class"));
        dataSource.setUrl(environment.getRequiredProperty("hibernate.connection.url"));
        dataSource.setUsername(environment.getRequiredProperty("hibernate.connection.username"));
        dataSource.setPassword(environment.getRequiredProperty("hibernate.connection.password"));

        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }
    /*
    раньше при тестировании мы создавали:
    configuration = new Configuration
    configuration.addAnnotatedClass....
    sessionFactory = configuration.buildSessionFactory
    sessionFactory.getCurrentSession

    теперь Spring
        подгружает конфигурацию
        Entity/s сканируются и подгружаются автоматически
        создается sessionFactory
        и теперь нам надо просто получить сессию из уже созданной и сконфигурированной sessionFactory
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.dschepkin.library.model");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    /*
    Конфигурируем менеджер транзакций
    Теперь мы не будем вручную открывать и закрывать транзакции, за нас это будет Spring
    Этот Bean говорит Spring как работать с транзакциями
     */
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }
}
