package com.example.firstproject.objectmapper;

import lombok.*;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
public class Burger {
    public String name;
    private int price;
    private List<String> ingredients;
}
