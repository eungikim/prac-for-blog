package com.example.firstproject.sample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MyTest {

    @Test
    public void testAdd() {
        int result = 1 + 2;

        assertThat(result).isEqualTo(3);
    }
}
