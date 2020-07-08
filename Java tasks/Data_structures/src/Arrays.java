import java.util.ArrayList;
import java.util.List;

public class Arrays {
    public static void main(String[] args) {
        List<Integer> result = new ArrayList<>();
        result.add(2);
        result.add(3);
        result.add(2);
        result.add(3);
        result.add(5);
        result.add(6);
        List<Integer> res = deleteDyblicates(result);
        System.out.println(res);
    }

    public static int getMaxSum(int[] arr){
        int sum = 0;
        int maxsum = 0;
        for(int i = 0;i < arr.length;i++){
            sum += arr[i];
            if(maxsum < sum)
                maxsum = sum;
            else if (sum < 0)
                sum = 0;
        }
        return maxsum;
    }

    public static int secondMin(int[] array){
        int min1;
        int min2;
        if (array[0] > array[1]){
            min1 = 1;
            min2 = 0;
        }
        else{
            min1 = 0;
            min2 = 1;
        }
        for (int i = 2; i < array.length; i++){
            if (array[i] < array[min1]){
                int check = min1;
                min1 = i;
                if (array[check] < array[min2])
                    min2 = check;
            }
            if (array[i] < array[min2])
                min2 = i;

        }
        return array[min2];
    }

    public static List<Integer> unique(int[] array){
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++){
            boolean unique = true;
            for (int j = 0; j < array.length; j++){
                if (array[i] == array[j] && i != j){
                    unique = false;
                    break;
                }
            }
            if (unique)
                result.add(array[i]);
        }
        return result;
    }

    public static int[] changeNegative(int[] array){
        int tmp;
        for (int i = 0; i < array.length; i++){
            if (array[i] < 0){
                while (i > 0 && array[i - 1] > 0){
                    tmp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = tmp;
                    i = i -1;
                }

            }
        }
        return array;
    }

    public static List<Integer> deleteDyblicates(List<Integer> arry){
        List<Integer> copy = new ArrayList<>();
        for(int t = 0; t < arry.size(); t++){
            copy.add(arry.get(t));
        }
        System.out.println(copy);
        for (int i = 0; i < copy.size(); i++){
            for (int j = 0; j < copy.size(); j++){
                if (copy.get(j).equals(copy.get(i)) && i != j)
                    copy.remove(copy.get(j));
            }
        }
        return copy;
    }

}
