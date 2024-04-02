package me.absolute.spingcourse.Project2Boot.controllers;

import jakarta.validation.Valid;

import me.absolute.spingcourse.Project2Boot.model.Book;
import me.absolute.spingcourse.Project2Boot.model.Person;
import me.absolute.spingcourse.Project2Boot.services.BookService;
import me.absolute.spingcourse.Project2Boot.services.PeopleService;
import me.absolute.spingcourse.Project2Boot.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/library")
public class LibraryController {
    private final PeopleService peopleService;
    private final BookService bookService;

    private final PersonValidator personValidator;

    @Autowired
    public LibraryController(PeopleService peopleService, BookService bookService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.bookService = bookService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "library/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        List<Book> bookList = bookService.getBooksByOwnerId(id); // personDAO.getBookList(id);
        model.addAttribute("booklist", bookList);
        model.addAttribute("bookListSize", bookList.size());
        return "library/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "library/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors())
            return "library/new";
        peopleService.save(person);
        return "redirect:/library";

    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "library/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()) {
            return "library/edit";
        }
        peopleService.update(id, person);
        return "redirect:/library";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/library";
    }
}
