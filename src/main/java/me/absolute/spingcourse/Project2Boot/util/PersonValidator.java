package me.absolute.spingcourse.Project2Boot.util;

import me.absolute.spingcourse.Project2Boot.model.Person;
import me.absolute.spingcourse.Project2Boot.services.PeopleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(!person.hasCorrectName()) errors.rejectValue("fullname", " ", "The name should be in this format: Surname Name Patronymic");
        else if(!Character.isUpperCase(person.getName().codePointAt(0)) ||
        !Character.isUpperCase(person.getSurname().codePointAt(0)) ||
        !Character.isUpperCase(person.getLastName().codePointAt(0))) {
            errors.rejectValue("fullname", " ", "The first characters in full name should be in upper case");
        }
    }
}
