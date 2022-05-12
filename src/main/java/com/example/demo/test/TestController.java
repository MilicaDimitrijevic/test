package com.example.demo.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("test/v1")
public class TestController {
    private final GeneralDaoJPA<Person> daoJPA;

    public TestController() {
        this.daoJPA = new GeneralDaoJPA<>();
        daoJPA.setClazz(Person.class);
    }

    @RequestMapping(
            path = "/create1000",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> insert1000Person(@RequestBody List<Person> persons) {
        for (int i = 0; i < persons.size(); i++) {
            daoJPA.create(persons.get(i));
        }
        return ResponseEntity.ok("insert 100");
    }


    @RequestMapping(
            path = "/getall_stream",
            method = RequestMethod.GET
    )
    public ResponseEntity<Stream<PersonDto>> getPersonStream() {
        Stream<Person> stream = daoJPA.findAllAsStream();
        Stream<PersonDto> streamDto = stream.map(pdto -> {
            PersonDto personDto = new PersonDto();
            personDto.setEmail(pdto.getEmail());
            return personDto;
        });//.forEach(System.out::println);
        return ResponseEntity.ok(streamDto);

    }

    @RequestMapping(
            path = "/getall_list",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<PersonDto>> getPersonList() {
        List<Person> personList = daoJPA.findAllAsList();
        List<PersonDto> personDtoList = new ArrayList<>(personList.size());
        for (Person person : personList) {
            PersonDto personDto = new PersonDto();
            personDto.setEmail(person.getEmail());
            personDtoList.add(personDto);
        }
        return ResponseEntity.ok(personDtoList);

    }
}
