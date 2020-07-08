package com.algorithm.sort;

public class QuickSort{

    public static int[] sort(int[] array){
        quickSort(array, 0, array.length);
        return array;
    }

    public static void quickSort(int[] array,int start, int end){
        if (start >= end)
            return;
        int p = partition(array, start, end);
        quickSort(array,start, p);
        quickSort(array, p + 1, end);
    }


    public static int partition(int[] array, int start, int end){
        int x = array[start];
        int j = start;
        for (int i = start + 1; i < end;i++){
            if (array[i] <= x){
                j = j + 1;
                int tmp = array[j];
                array[j] = array[i];
                array[i] = tmp;
            }
        }
        int cmp = array[start];
        array[start] = array[j];
        array[j] = cmp;
        return j;
    }

}
