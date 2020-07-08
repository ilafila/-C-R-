package com.algorithm.find;

public class LinFind implements Findable{

    private int[] array;
    private int findElement;

    public LinFind(int[] array,int findElement){
        this.array = array;
        this.findElement = findElement;
    }

    @Override
    public int find(){
        for (int i = 0;i < array.length;i++) {
            if (array[i] == findElement)
                return i;
        }
        return -1;
    }
}
