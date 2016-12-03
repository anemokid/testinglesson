package com.ekc.c4q.testinglesson.util;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AdderTest {

    private Adder adder;

    @Before
    public void setUp() {
        adder = new Adder();
    }

    @Test
    public void add_whenOneInputIsZero_shouldReturnSecondNumber() {
        // When
        int result = adder.add(0, 7);

        // Then
        assertThat(result).isEqualTo(7);
    }

    @Test
    public void add_whenSecondInputIsZero_shouldReturnFirstNumber() {
        // When
        int result = adder.add(7, 0);

        // Then
        assertThat(result).isEqualTo(7);
    }

    @Test
    public void add_whenInputsAreNegative_shouldReturnNegativeNumber() {
        // When
        int result = adder.add(-4, -10);

        // Then
        assertThat(result).isEqualTo(-14);
    }

    @Test
    public void add_whenInputsArePositive_shouldReturnPositiveNumber() {
        // When
        int result = adder.add(4, 10);

        // Then
        assertThat(result).isEqualTo(14);
    }
}