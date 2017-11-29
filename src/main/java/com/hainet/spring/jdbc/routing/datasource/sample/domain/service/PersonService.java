package com.hainet.spring.jdbc.routing.datasource.sample.domain.service;

import com.hainet.spring.jdbc.routing.datasource.sample.domain.dao.PersonDao;
import com.hainet.spring.jdbc.routing.datasource.sample.domain.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonDao dao;

    @Transactional
    public List<Person> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Person> findAllWithReadOnly() {
        return dao.findAll();
    }

    @Transactional
    public int insert(final Person person) {
        return dao.insert(person);
    }

    @Transactional(readOnly = true)
    public int insertWithReadOnly(final Person person) {
        return dao.insert(person);
    }
}
