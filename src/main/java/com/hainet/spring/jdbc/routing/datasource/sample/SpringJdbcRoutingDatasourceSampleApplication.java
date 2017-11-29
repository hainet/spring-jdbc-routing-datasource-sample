package com.hainet.spring.jdbc.routing.datasource.sample;

import com.hainet.spring.jdbc.routing.datasource.sample.domain.entity.Person;
import com.hainet.spring.jdbc.routing.datasource.sample.domain.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringJdbcRoutingDatasourceSampleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcRoutingDatasourceSampleApplication.class, args);
    }

    private final PersonService service;

    @Override
    public void run(final String... args) throws Exception {
        // master
        final Person master = new Person();
        master.setName("person");
        service.insert(master);
        service.findAll().forEach(System.out::println);

        // slave
        final Person slave = new Person();
        slave.setName("person");
        service.insertWithReadOnly(slave);
        service.findAllWithReadOnly().forEach(System.out::println);
    }
}
