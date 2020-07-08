package com.algorithm.sort;

import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        int[] test = new int[] {1,1,5,5,2,3,2,9};
        int[] result = QuickSort.sort(test);
        System.out.println(Arrays.toString(result));
    }
}
