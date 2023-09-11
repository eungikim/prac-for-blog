package com.example.firstproject.test;

import lombok.Builder;
import lombok.Data;

public class Main {
    public static void main(String[] args) {
        Person person = Person.builder()
                .firstName("John")
                .lastName("Doe")
                .age(30)
                .build();
        System.out.println("person = " + person);
    }
}

@Builder
@Data
class Person {
    private String firstName;
    private String lastName;
    private int age;
}
