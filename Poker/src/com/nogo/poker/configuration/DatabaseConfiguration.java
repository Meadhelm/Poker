package com.nogo.poker.configuration;

import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

  @Resource
  private Environment env;

  private Properties hibernateProperties() {

    final Properties props = new Properties();
    props.setProperty("current_session_context_class", "thread");
    props.setProperty("hibernate.cache_provider_class", "org.hibernate.cache.NoCacheProvider");
    props.setProperty("exposeTransactionAwareSessionFactory", "false");
    props.setProperty("hibernate.jdbc.batch_size", "5");
    props.setProperty("hibernate.connection.SetBigStringTryClob", "true");
    props.setProperty("hibernate.query.substitutions", "true 1, false 0");
    props.setProperty("hibernate.show_sql", "true");
    props.setProperty("hibernate.format_sql", "true");
    props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    return props;
  }

  /**
   * Configuration for the DataSource.
   *
   * @return datasource
   */
  @Bean
  public DataSource dataSource() {
    final JdbcDataSource ds = new JdbcDataSource();
    ds.setUrl("jdbc:h2:mem:DATABASE;DB_CLOSE_DELAY=-1");
    ds.setUser("sa");
    ds.setPassword("sa");
    return ds;
  }

  /**
   * Constructs a new database session factory used by other Spring beans.
   *
   * @return local session factory scan package com.nogo.poker
   */
  @Bean
  public SessionFactory databaseSessionFactory() {
    return new LocalSessionFactoryBuilder(dataSource()).scanPackages("com.nogo.poker")
        .addProperties(hibernateProperties())
        .buildSessionFactory();
  }

  /**
   * Configuration for the HibernateTransactionManager.
   *
   * @return transaction manager
   */
  @Bean
  public HibernateTransactionManager databaseTransactionManager() {
    final HibernateTransactionManager htm = new HibernateTransactionManager();
    htm.setSessionFactory(databaseSessionFactory());
    return htm;
  }
}
