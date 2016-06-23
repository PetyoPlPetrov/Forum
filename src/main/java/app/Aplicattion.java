package app;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

/**
 * Created by PetyoPetrov on 24.05.2016 г..
 */
@SpringBootApplication
public class Aplicattion {
    public static void main(String[] args) {

        SpringApplication.run(Aplicattion.class, args);

    }

    @Bean
    public EntityManagerFactory createEMF() {
        return Persistence.createEntityManagerFactory("demo");
    }

    @Bean
    public EntityManager createEM() {
        return this.createEMF().createEntityManager();
    }

    @Bean
    public PlatformTransactionManager createTm() {
        return new JpaTransactionManager();
    }

    @Bean
    public DataSource dataSource() {
        return new MysqlDataSource();
    }
}
