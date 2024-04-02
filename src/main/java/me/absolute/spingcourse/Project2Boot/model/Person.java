package me.absolute.spingcourse.Project2Boot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity //jpa
@Table(name = "person") // jpa
public class    Person {
    @Id //jpa
    @Column(name = "id")//jpa
    @GeneratedValue(strategy = GenerationType.IDENTITY)//jpa
    private int person_id;

    @NotEmpty(message = "Name should not be empty") //validator
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")//validator

    @Column(name = "full_name")
    private String fullName;


    @Column(name = "date_of_birth")//jpa
    @Temporal(TemporalType.DATE) //jpa
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Book> bookList;

    public Person() {
    }

    public Person(int person_id, String fullName, Date dateOfBirth, List<Book> bookList) {
        this.person_id = person_id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.bookList = bookList;
    }

    public int getId() {
        return person_id;
    }

    public void setId(int person_id) {
        this.person_id = person_id;
    }

    public void putBook(Book book){
        this.bookList.add(book);
    }

    public void passBook(Book book) {
        this.bookList.add(book);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return fullName.split(" ")[0];
    }

    public String getSurname() {
        return fullName.split(" ")[1];
    }

    public String getLastName() {
         return fullName.split(" ")[2];
    }

    public boolean hasCorrectName() {
        String[] nameLine = fullName.split(" ");
        if(nameLine.length == 3) {
            return true;
        }
        else return false;
    }

}
