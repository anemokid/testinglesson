package com.ekc.c4q.testinglesson.util;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class IntegerSorterTest {

    @Test
    public void sort_whenEmpty_shouldReturnEmptyArray() {
        int[] input = { };
        int[] result = IntegerSorter.sort(input);
        assertThat(result).isEmpty();
    }

    @Test
    public void sort_whenSmallValuesCloseTogether_shouldReturnSortedArray() {
        int[] input = {4, 3, 2, 1};

        int[] result = IntegerSorter.sort(input);
        assertThat(result).containsExactly(1, 2, 3, 4);
    }

    @Test
    public void sort_smallValuesWithRepeats_shouldReturnSortedArray() {
        int[] input = {2, 2, 4, 1, 3, 2, 1, 4};

        int[] result = IntegerSorter.sort(input);
        assertThat(result).containsExactly(1, 1, 2, 2, 2, 3, 4, 4);
    }

    @Test
    public void sort_smallNegativeValuesWithRepeats_shouldReturnSortedArray() {
        int[] input = {-2, -2, -4, -1, -3, -2, -1, -4};

        int[] result = IntegerSorter.sort(input);
        assertThat(result).containsExactly(-4, -4, -3, -2, -2, -2, -1, -1);
    }

    @Test
    public void sort_largeValuesCloseTogether_shouldReturnSortedArray() {
        int[] input = {40_000_000, 40_000_400, 40_000_440, 40_000_444};

        int[] result = IntegerSorter.sort(input);
        assertThat(result).containsExactly(40_000_000, 40_000_400, 40_000_440, 40_000_444);
    }

    @Test
    public void sort_largeValuesCloseTogetherWithRepeats_shouldReturnSortedArray() {
        int[] input = {40_000_000,
                40_000_444,
                40_000_440,
                40_000_400,
                40_000_444,
                40_000_400,
                40_000_400
        };

        int[] result = IntegerSorter.sort(input);
        assertThat(result).containsExactly(40_000_000,
                40_000_400,
                40_000_400,
                40_000_400,
                40_000_440,
                40_000_444,
                40_000_444);
    }

    @Test
    public void sort_largeNegativeValuesCloseTogetherWithRepeats_shouldReturnSortedArray() {
        int[] input = {-40_000_000,
                -40_000_444,
                -40_000_400,
                -40_000_440,
                -40_000_400,
                -40_000_400,
                -40_000_444
        };

        int[] result = IntegerSorter.sort(input);
        assertThat(result).containsExactly(-40_000_444,
                -40_000_444,
                -40_000_440,
                -40_000_400,
                -40_000_400,
                -40_000_400,
                -40_000_000);
    }

    @Test
    public void sort_wideRangeWithNegativeNumbersAndRepeats_shouldReturnSortedArray() {
        int[] input = {40_000_000,
                0,
                0,
                -30,
                3_000_999,
                -70_000_000,
                4,
                -30,
                0
        };

        int[] result = IntegerSorter.sort(input);

        assertThat(result)
                .containsExactly(-70_000_000, -30, -30, 0, 0, 0, 4, 3_000_999, 40_000_000);
    }
}
