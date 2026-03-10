package com.harsh.batch.batch;

import com.harsh.batch.entities.Person;
import org.springframework.batch.infrastructure.item.ItemProcessor;

public class PersonProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person person) throws Exception {
        person.setFirstName(person.getFirstName().toUpperCase());
        person.setLastName(person.getLastName().toUpperCase());
        return person;
    }

}
