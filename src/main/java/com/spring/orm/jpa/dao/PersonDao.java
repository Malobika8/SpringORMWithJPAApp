package com.spring.orm.jpa.dao;

import com.spring.orm.jpa.entity.Person;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class PersonDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void save(Person person) {
        entityManager.persist(person);
    }
}
