package me.absolute.spingcourse.Project2Boot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
//    private final JdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM PERSON", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public void create(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(fullname, birthday) VALUES (?, ?)", person.getFullName(), person.getDateOfBirth());
//    }
//
//    public void update(int id, Person updatePerson) {
//        jdbcTemplate.update("UPDATE Person SET fullname=?, birthday=? WHERE person_id=?", updatePerson.getFullName(), updatePerson.getDateOfBirth(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
//    }
//
//    public List<Book> getBookList(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id =?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
//    }

}
