package com.york.easywrite;

import com.alibaba.fastjson.JSON;
import com.york.easywrite.model.Person;
import com.york.easywrite.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EasywriteApplicationTests {

    @Autowired
    private PersonService personService;

    @Test
    void contextLoads() {
    }

    @Test
    void save(){
        Person person=new Person();
        person.setName("lisi");
        person.setAge(11);
        person.setAddress("深圳市宝安区158号");
        person.setTelephone(132112);
        personService.insert(person);
    }

    @Test
    void get(){
        Person person = personService.getPerson(1);
        System.out.print(JSON.toJSONString(person));
    }





}
