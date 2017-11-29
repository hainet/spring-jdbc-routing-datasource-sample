package com.hainet.spring.jdbc.routing.datasource.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource master() {
        return new EmbeddedDatabaseBuilder()
                .setName("master")
                .setType(EmbeddedDatabaseType.H2)
                .addScripts(
                        "schema-master.sql",
                        "data-master.sql")
                .build();
    }

    @Bean
    public DataSource slave() {
        return new EmbeddedDatabaseBuilder()
                .setName("slave")
                .setType(EmbeddedDatabaseType.H2)
                .addScripts(
                        "schema-slave.sql",
                        "data-slave.sql")
                .build();
    }

    @Bean
    @Primary
    @DependsOn({"master", "slave"})
    public DataSource routingDataSource(final DataSource master, final DataSource slave) {
        final RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(new HashMap<Object, Object>() {{
            {
                put("master", master);
                put("slave", slave);
            }
        }});
        routingDataSource.afterPropertiesSet();

        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
}
