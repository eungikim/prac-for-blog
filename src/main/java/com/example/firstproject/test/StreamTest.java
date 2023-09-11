package com.example.firstproject.test;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StreamTest {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> list2 = list1.stream().map((x) -> x*x).collect(Collectors.toList());
        log.info("list2: {}", list2);

        List<String> list3 = Arrays.asList("fdsbe","bdea","cffg","deaqd","eerth","fhhfd", "abc", "oooooa");
        list3.stream()
                .filter(name -> name.contains("a"))
                .sorted()
                .forEach(System.out::println);

    }
}
