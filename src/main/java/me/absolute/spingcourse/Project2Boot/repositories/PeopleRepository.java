package me.absolute.spingcourse.Project2Boot.repositories;


import me.absolute.spingcourse.Project2Boot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findByFullNameStartingWith(String startingWith);

}
