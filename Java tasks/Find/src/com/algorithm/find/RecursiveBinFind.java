package com.algorithm.find;

public class RecursiveBinFind {
    public static int recursive(int[] array, int findElement, int start, int end){
        int mid = start + (end - start) / 2;

        if (start >= end)
            return -(1 + start);

        if (array[start] == findElement)
            return start;

        if (array[mid] == findElement)
        {
            if (mid == start + 1)
                return mid;
            else
                return recursive(array, findElement, start, mid + 1);
        }

        else if ((array[mid] > findElement))
            return recursive(array, findElement, start, mid);
        else
            return recursive(array, findElement, mid + 1, end);
    }
}
