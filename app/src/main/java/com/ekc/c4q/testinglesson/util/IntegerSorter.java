package com.ekc.c4q.testinglesson.util;


import java.util.Arrays;

import static com.ekc.c4q.testinglesson.util.IntegerSorter.Strategy.DEFAULT;

public class IntegerSorter {

    /**
     * Return a sorted array of integers.
     *
     * @param input the unsorted input.
     * @return the sorted array.
     */
    public static int[] sort(int[] input) {
        // Run test (IntegerSorterTest) and verify tests pass using Java's sort function
        // Remove/Comment out the sort method
//        Arrays.sort(input);

        // Write your own sort algorithm and run the tests to verify your sort works
        return sort(input, DEFAULT);
    }

    private static int[] sort(int[] input, Strategy strategy) {
        switch (strategy) {
            default:
            case DEFAULT:
            case RADIX:
                return radixSort(input);

            case BUBBLE_SORT:
            case QUICK_SORT:
            case MERGE_SORT:
                throw new IllegalArgumentException("Sort not implemented yet. Exercise left for students!");
        }
    }

    /**
     * Sort integers using LSD radix sort strategy.
     * https://en.wikipedia.org/wiki/Radix_sort
     *
     * @param input the unsorted input.
     * @return the sorted array.
     */
    private static int[] radixSort(int[] input) {
        int[] buffer;
        int[] negativesBuffer;

        int totalNegative = 0;
        for (int i : input) {
            if (i < 0) {
                totalNegative++;
            }
        }

        for (int digitsPlace = 1; digitsPlace <= 1_000_000_000; digitsPlace *= 10) {

            buffer = new int[input.length - totalNegative];
            negativesBuffer = new int[totalNegative];

            // Each index represents a digitsPlace from 0-9
            int[] count = new int[10];
            int[] negatives = new int[10];

            // Count how many values are in that digits place
            for (int i : input) {
                int digit = (i / digitsPlace) % 10;
                if (digit < 0) {
                    negatives[Math.abs(digit)]++;
                } else {
                    count[digit]++;
                }
            }

            // Scale up each count so now it represents the right index in sorted array
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
                negatives[i] += negatives[i - 1];
            }

            for (int i = input.length - 1; i >= 0; i--) {
                int digit = (input[i] / digitsPlace) % 10;
                if (digit < 0) {
                    digit = Math.abs(digit);
                    negativesBuffer[negatives[digit] - 1] = input[i];
                    negatives[digit]--;
                } else {
                    buffer[count[digit] - 1] = input[i];
                    count[digit]--;
                }
            }

            for (int i = totalNegative - 1; i >= 0; i--) {
                input[totalNegative - i - 1] = negativesBuffer[i];
            }

            for (int i = 0; i < buffer.length; i++) {
                input[totalNegative + i] = buffer[i];
            }
        }
        return input;
    }

    enum Strategy {
        DEFAULT,
        RADIX,
        BUBBLE_SORT,
        QUICK_SORT,
        MERGE_SORT
    }
}
