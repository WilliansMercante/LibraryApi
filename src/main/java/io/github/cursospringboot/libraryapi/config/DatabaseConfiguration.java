package io.github.cursospringboot.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    //@Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }

    @Bean
    public DataSource hikariDatasource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10); // maximo de 10 conexões
        config.setMinimumIdle(1); // tamanho inicial do pool
        config.setPoolName("library-db-pool-wilaoquefez"); // aparece no log
        config.setMaxLifetime(600000); // 600 mil ms é 10 minutos
        config.setConnectionTimeout(100000); // timeout para conseguir uma conexão
        config.setConnectionTestQuery("select 1"); // query de teste

        return new HikariDataSource(config);
    }

}
