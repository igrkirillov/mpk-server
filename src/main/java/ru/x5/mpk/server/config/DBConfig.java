package ru.x5.mpk.server.config;

import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

    @Value("${mpk.dburl}")
    private String url;

    @Value("${mpk.dbuser}")
    private String user;

    @Value("${mpk.dbpassword}")
    private String password;

    @Bean
    public javax.sql.DataSource getDataSource() {
        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setURL(url);;
        source.setUser(user);
        source.setPassword(password);
        source.setMaxConnections(10);
        return source;
    }
}
