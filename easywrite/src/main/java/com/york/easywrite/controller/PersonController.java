package com.york.easywrite.controller;


import com.york.easywrite.model.Person;
import com.york.easywrite.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author York
 * @since 2021-07-10
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/get/{id}")
    public Person get(@PathVariable Integer id){
        return personService.getPerson(id);
    }

    @PostMapping(value = "/save")
    public int save(@RequestBody Person person){
        return personService.insert(person);
    }

}

