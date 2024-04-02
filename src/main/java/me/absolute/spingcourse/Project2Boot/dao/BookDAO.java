package me.absolute.spingcourse.Project2Boot.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//    public List<Book> index() {
//        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
//    }
//
//    public Book show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id},
//                new BookMapper()).stream().findAny().orElse(null);
//    }
//
//    public void create(Book book){
//        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES (?, ?, ?)",
//                book.getName(), book.getAuthor(), book.getYear());
//    }
//
//    public void update(int id, Book updateBook) {
//        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE book_id=?", updateBook.getName(), updateBook.getAuthor(), updateBook.getYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
//    }
//
//    public void addOwner(int id, Person person) {
//        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id =?", person.getId(), id);
//    }
//
//    public void deleteOwner(int id) {
//        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE book_id=?", id);
//    }



}
