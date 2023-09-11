package com.example.firstproject.sample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CalTest {

    Calculator calculator = new Calculator();

    @Test
    void add() {
        assertThat(calculator.add(2, 3)).isEqualTo(15);
    }

    @Test
    void substract() {
        assertThat(calculator.substract(2, 3)).isEqualTo(44);
    }
}


class Calculator {
    int a;
    int b;

    public int add(int a, int b) {
        return a + b;
    }

    public int substract(int a, int b) {
        return a - b;
    }
}
