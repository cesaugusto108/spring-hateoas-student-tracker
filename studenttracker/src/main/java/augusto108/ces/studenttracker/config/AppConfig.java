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
import java.util.Arrays;
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

    @Bean
    public DataSource configDataSource() {
        final int initialPoolSize = Integer.parseInt(Objects.requireNonNull(env.getProperty("connection.pool.initialPoolSize")));
        final int minPoolSize = Integer.parseInt(Objects.requireNonNull(env.getProperty("connection.pool.minPoolSize")));
        final int maxPoolSize = Integer.parseInt(Objects.requireNonNull(env.getProperty("connection.pool.maxPoolSize")));
        final int maxIdleTime = Integer.parseInt(Objects.requireNonNull(env.getProperty("connection.pool.maxIdleTime")));

        for (Integer integer : Arrays.asList(initialPoolSize, minPoolSize, maxPoolSize, maxIdleTime)) {
            LOGGER.info("Data source property: " + integer);
        }

        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.password"));


        dataSource.setInitialPoolSize(initialPoolSize);
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
