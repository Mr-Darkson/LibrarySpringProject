package me.absolute.spingcourse.Project2Boot.repositories;


import me.absolute.spingcourse.Project2Boot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBookByOwnerId(int id);
    List<Book> findByNameStartingWith(String name);



}
