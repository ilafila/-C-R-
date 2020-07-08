package com.algorithm.find;

public class FindTest {
    public static void main(String[] args) {
        int[] testArr = new int[] {0,2,3,3,5,8,8};
        int findEl = 8;
        int start = 0;
        int end = testArr.length;
        int result = RecursiveBinFind.recursive(testArr, findEl, start, end);
        System.out.println(result);
    }
}
