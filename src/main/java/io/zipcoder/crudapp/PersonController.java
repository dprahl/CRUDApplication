package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by danielprahl on 6/16/17.
 */

@RestController
public class PersonController {

    private PersonRepository people;

    @Autowired
    public PersonController(PersonRepository people) {
        this.people = people;
    }

    @RequestMapping(value="/person/{id}", method = RequestMethod.GET)
    public Person readPerson(@PathVariable Integer id) {
        return this.people.findOne(id);
    }

    @RequestMapping(value="/person/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable Integer id){
        people.delete(id);
    }

    @RequestMapping(value="/person/", method = RequestMethod.POST)
    public void createPerson(String name, Integer age){
        people.save(new Person(name, age));
    }

    @RequestMapping(value="/person/{id}", method = RequestMethod.PUT)
    public void updatePerson(@PathVariable Integer id, String name, Integer age){
        if(people.exists(id)){
            // if person exists, update with new values
            people.findOne(id).setName(name);
            people.findOne(id).setAge(age);
        }else{
            // if person does not exist, create one
            people.save(new Person(name, age));
        }
    }

}
