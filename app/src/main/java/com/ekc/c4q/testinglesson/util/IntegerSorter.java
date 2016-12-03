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

  static int[] sort(int[] input, Strategy strategy) {
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
   * Sort integers using LSD radix sort strategy. Sorts positive and negative values separately,
   * then merges the two.
   * https://en.wikipedia.org/wiki/Radix_sort
   *
   * @param input the unsorted input.
   * @return the sorted array.
   */
  private static int[] radixSort(int[] input) {
    int[] positiveInput;
    int[] negativeInput;

    int totalNegative = 0;
    for (int i : input) {
      if (i < 0) {
        totalNegative++;
      }
    }

    positiveInput = new int[input.length - totalNegative];
    negativeInput = new int[totalNegative];
    int positiveIndex = 0;
    int negativeIndex = 0;

    for (int i : input) {
      if (i >= 0) {
        positiveInput[positiveIndex++] = i;
      } else {
        negativeInput[negativeIndex++] = i;
      }
    }

    positiveInput = absValueRadixSort(positiveInput);
    negativeInput = absValueRadixSort(negativeInput);

    int[] output = new int[input.length];
    for (int i = totalNegative - 1; i >= 0; i--) {
      output[totalNegative - i - 1] = negativeInput[i];
    }

    for (int i = 0; i < positiveInput.length; i++) {
      output[totalNegative + i] = positiveInput[i];
    }

    return output;
  }

  private static int[] absValueRadixSort(int[] input) {
    int[] output;
    for (int digitsPlace = 1; digitsPlace <= 1_000_000_000; digitsPlace *= 10) {

      output = new int[input.length];

      // Each index represents a digitsPlace from 0-9
      int[] count = new int[10];

      // Count how many values are in that digits place
      for (int i : input) {
        int digit = Math.abs((i / digitsPlace) % 10);
        count[digit]++;
      }

      // Scale up each count so now it represents the right index in sorted array
      for (int i = 1; i < count.length; i++) {
        count[i] += count[i - 1];
      }

      for (int i = input.length - 1; i >= 0; i--) {
        int digit = Math.abs((input[i] / digitsPlace) % 10);
        output[count[digit] - 1] = input[i];
        count[digit]--;
      }

      input = Arrays.copyOf(output, output.length);
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
