package me.absolute.spingcourse.Project2Boot.services;

import me.absolute.spingcourse.Project2Boot.model.Book;
import me.absolute.spingcourse.Project2Boot.model.Person;
import me.absolute.spingcourse.Project2Boot.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final PeopleService peopleService;

    @Autowired
    public BookService(BookRepository bookRepository, PeopleService peopleService) {
        this.bookRepository = bookRepository;
        this.peopleService = peopleService;
    }
    public List<Book> findAll(int page, int books_count, boolean sort_by_year) {
        if(sort_by_year) {
            return bookRepository.findAll(PageRequest.of(page, books_count, Sort.by("year"))).getContent();
        }
        return bookRepository.findAll(PageRequest.of(page,books_count)).getContent();
    }

    public Book findOne(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }
    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void addOwner(int id, Person person) {
        Book book = findOne(id);
        person = peopleService.findOne(person.getId());
        if(book.hasOwner()) {
            System.out.println("Книгу уже взял другой человек. Необходимо сначала освободить её.");
        }
        else {
            book.setOwner(person);
            save(book);
        }
    }

    @Transactional
    public void deleteOwner(int id) {
        Book book = findOne(id);
        if(book.hasOwner()) {
            book.setOwner(null);
            save(book);
        }
    }

    public List<Book> getBooksByOwnerId(int id) {
        return bookRepository.findBookByOwnerId(id);
    }

    public List<Book> findByNameStartingWith(String partOfName) {
        return bookRepository.findByNameStartingWith(partOfName);
    }

}
