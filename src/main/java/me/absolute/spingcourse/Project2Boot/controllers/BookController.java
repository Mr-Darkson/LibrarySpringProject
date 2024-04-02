package me.absolute.spingcourse.Project2Boot.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import me.absolute.spingcourse.Project2Boot.model.Book;
import me.absolute.spingcourse.Project2Boot.model.Person;
import me.absolute.spingcourse.Project2Boot.services.BookService;
import me.absolute.spingcourse.Project2Boot.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }
    @GetMapping()
    public String index(HttpServletRequest request, Model model) { //@RequestParam("page") int page, @RequestParam("books_per_page") int booksCnt
        int page = 0;
        int books_count = 5;
        boolean sort_by_year = false;

        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        if(request.getParameter("books_per_page") != null)
            books_count = Integer.parseInt(request.getParameter("books_per_page"));
        if(request.getParameter("sort_by_year") != null &&
                request.getParameter("sort_by_year").equals("true"))
            sort_by_year = true;

        //System.out.println("Page:" + page + " | Books count:" + books_count + " | Sort by year:" + sort_by_year);
        model.addAttribute("books", bookService.findAll(page, books_count, sort_by_year));
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        model.addAttribute("people", peopleService.findAll());
        model.addAttribute("person", book.hasOwner() ? book.getOwner() : new Person());


        return "book/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "/book/new";
        else {
            bookService.save(book);
            return "redirect:/book";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findOne(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "book/edit";
        }
        bookService.update(id, book);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/addOwner")
    public String addOwner(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookService.addOwner(id, person);
        return "redirect:/book/" + id;
    }

    @PatchMapping("/{id}/deleteOwner")
    public String deleteOwner(@PathVariable("id") int id) {
        bookService.deleteOwner(id);
        return "redirect:/book/" + id;
    }

    @GetMapping("/search")
    public String search() {
        return "book/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String name) {
        model.addAttribute("books", bookService.findByNameStartingWith(name));
        return "book/search";
    }


}
