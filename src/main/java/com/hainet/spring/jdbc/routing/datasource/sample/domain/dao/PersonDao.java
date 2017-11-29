package com.hainet.spring.jdbc.routing.datasource.sample.domain.dao;

import com.hainet.spring.jdbc.routing.datasource.sample.domain.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonDao {

    private final JdbcTemplate jdbc;

    public List<Person> findAll() {
        return jdbc.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public int insert(final Person person) {
        return jdbc.update("INSERT INTO person (name) VALUES (?)", person.getName());
    }
}
