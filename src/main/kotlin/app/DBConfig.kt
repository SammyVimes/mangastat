package app

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import java.util.*
import javax.inject.Inject
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

/**
 * Created by Semyon on 02.01.2016.
 */
@Configuration
@EnableJpaRepositories("repository")
open class DBConfig {

    @Inject
    private var environment: Environment? = null;

    @Bean
    @ConfigurationProperties(prefix = "datasource.mine")
    open public fun dataSource(): DataSource {
        val env = environment!!;

        val url = env.getProperty("db.url");
        val user = env.getProperty("db.username");
        val pass = env.getProperty("db.password");
        val driverClassName = env.getProperty("db.driver-class-name");

        return DataSourceBuilder
                .create()
                .username(user)
                .password(pass)
                .url(url)
                .driverClassName(driverClassName)
                .build();
    }

    @Bean
    open public fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        var emfb = LocalContainerEntityManagerFactoryBean();
        emfb.dataSource = dataSource;
        emfb.setPackagesToScan("model");
        emfb.jpaVendorAdapter = jpaVendorAdapter();
        emfb.jpaPropertyMap = jpaPropertiesMap();
        return emfb;
    }

    @Bean
    open public fun jpaVendorAdapter(): JpaVendorAdapter {
        return HibernateJpaVendorAdapter();
    }

    @Bean
    open public fun transactionManager(emf: EntityManagerFactory): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager();
        transactionManager.entityManagerFactory = emf;
        return transactionManager;
    }

    private fun jpaPropertiesMap(): Map<String, Any> {
        val properties = HashMap<String, Any>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

}

