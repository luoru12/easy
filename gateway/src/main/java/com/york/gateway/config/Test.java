package com.york.gateway.config;

import com.york.gateway.dto.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Test {


    public static void main(String[] args) {

        List<String> list =new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.parallelStream();

        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(14);
//        stream2.forEach(System.out::println);

        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
//        stream3.forEach(System.out::println);


        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200, "female", "New York"));
        personList.add(new Person("Owen", 9500, "male", "New York"));
        personList.add(new Person("Alisa", 7900, "female", "New York"));

        List<Integer> integers = Arrays.asList(7, 4, 6, 0, 9, 1);
        integers.stream().filter(o->o>6).forEach(System.out::println);
        boolean present = integers.stream().filter(o -> o > 6).findFirst().isPresent();


    }
}
