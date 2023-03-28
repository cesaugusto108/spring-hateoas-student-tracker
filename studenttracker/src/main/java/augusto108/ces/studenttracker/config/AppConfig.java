package augusto108.ces.studenttracker.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan("augusto108.ces.studenttracker")
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class AppConfig {
    private final static Logger LOGGER = Logger.getLogger(AppConfig.class.getName());

    private final Environment env;

    public AppConfig(Environment env) {
        this.env = env;
    }

    private static void log(String jdbcUrl, int initPoolSize, int minPoolSize, int maxPoolSize, int maxIdleTime) {
        LOGGER.info("-> URL: " + jdbcUrl);
        LOGGER.info("-> Initial pool size: " + initPoolSize);
        LOGGER.info("-> Min pool size: " + minPoolSize);
        LOGGER.info("-> Max pool size: " + maxPoolSize);
        LOGGER.info("-> Max idle time: " + maxIdleTime);
    }

    private String getPropertyInt(String property) {
        return Objects.requireNonNull(env.getProperty(property));
    }

    @Bean
    public DataSource configDataSource() {
        final String jdbcDriver = env.getProperty("jdbc.driver");
        final String jdbcUrl = env.getProperty("jdbc.url");
        final String jdbcUser = env.getProperty("jdbc.user");
        final String jdbcPassword = env.getProperty("jdbc.password");

        final int initPoolSize = Integer.parseInt(getPropertyInt("connection.pool.initialPoolSize"));
        final int minPoolSize = Integer.parseInt(getPropertyInt("connection.pool.minPoolSize"));
        final int maxPoolSize = Integer.parseInt(getPropertyInt("connection.pool.maxPoolSize"));
        final int maxIdleTime = Integer.parseInt(getPropertyInt("connection.pool.maxIdleTime"));

        log(jdbcUrl, initPoolSize, minPoolSize, maxPoolSize, maxIdleTime);

        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass(jdbcDriver);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(jdbcUser);
        dataSource.setPassword(jdbcPassword);
        dataSource.setInitialPoolSize(initPoolSize);
        dataSource.setMinPoolSize(minPoolSize);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setMaxIdleTime(maxIdleTime);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean configLocalSessionFactoryBean() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernateDialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernateShowSql", env.getProperty("hibernate.show_sql"));

        factoryBean.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
        factoryBean.setDataSource(configDataSource());
        factoryBean.setHibernateProperties(hibernateProperties);

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager configHibernateTransactionManager(@Autowired SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();

        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }
}
