package com.ekc.c4q.testinglesson.util;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AdderTest {

    @Test
    public void add_whenOneInputIsZero_shouldReturnSecondNumber() {
        // Given
        Adder adder = new Adder();

        // When
        int result = adder.add(0, 7);

        // Then
        assertThat(result).isEqualTo(7);
    }

    @Test
    public void add_whenSecondInputIsZero_shouldReturnFirstNumber() {
        // Given
        Adder adder = new Adder();

        // When
        int result = adder.add(7, 0);

        // Then
        assertThat(result).isEqualTo(7);
    }

    @Test
    public void add_whenInputsAreNegative_shouldReturnNegativeNumber() {
        // Given
        Adder adder = new Adder();

        // When
        int result = adder.add(-4, -10);

        // Then
        assertThat(result).isEqualTo(-14);
    }

    @Test
    public void add_whenInputsArePositive_shouldReturnPositiveNumber() {
        // Given
        Adder adder = new Adder();

        // When
        int result = adder.add(4, 10);

        // Then
        assertThat(result).isEqualTo(14);
    }
}