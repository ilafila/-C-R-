package com.algorithm.find;

public class BinFind implements Findable {

    private int[] array;
    private int findElement;
    private int start;
    private int end;

    public BinFind(int[] array,int findElement, int start,int end){
        this.array = array;
        this.findElement = findElement;
        this.start = start;
        this.end = end;
    }

    @Override
    public int find(){
        while (start <= end) {
            if (array[start] == findElement)
                return start;
            int middle = start + (end - start) / 2;
            if (array[middle] == findElement){
                if (middle == start + 1)
                    return middle;
                else
                    end = middle + 1;
            }
            if (findElement > array[middle])
                start = middle +1;
            if (findElement < array[middle])
                end = middle -1;
        }
        return -1;
    }
}
