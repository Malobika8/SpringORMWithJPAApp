package com.spring.orm.jpa;

import com.spring.orm.jpa.config.AppConfig;
import com.spring.orm.jpa.dao.PersonDao;
import com.spring.orm.jpa.entity.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrmWithJpaDemoApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        PersonDao personDao = context.getBean("personDao", PersonDao.class);

        //Save person
        Person person = new Person();
        person.setName("Pushpa");
        person.setAddress("Bangalore");

        System.out.println("Saving.. ");
        personDao.save(person);
    }
}
