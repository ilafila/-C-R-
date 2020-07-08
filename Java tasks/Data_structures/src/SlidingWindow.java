import java.util.ArrayList;
import java.util.List;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class SlidingWindow {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите длину массива");
        int arrSize = in.nextInt();

        int[] test = new int[arrSize];

        for (int j = 0 ; j< arrSize;j++){
            test[j] = in.nextInt();
        }

        System.out.println("Введите длину окна");
        int winsize = in.nextInt();
        in.close();

        List<Integer> result = maxWithDeque(test,winsize,arrSize);
        System.out.println(result);
    }

    public static List<Integer> min(int[] array, int m){
        List<Integer> minElements = new ArrayList<>();
        for (int i = 0; i < array.length; i++){
            int min = i;
            for (int j = i; j < m; j++){
                if (array[j] < array[min])
                    min = j;
            }
            minElements.add(array[min]);
            m = m + 1;
            if (m == array.length + 1)
                break;
        }
        return minElements;
    }


    public static List<Integer> maxWithDeque(int[] arr, int windowSize, int arrSize){

        List<Integer> maxElements = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();

        for(int i = 0; i < arrSize; i++){
            if (i >= windowSize){
                int max = deque.peekFirst();
                maxElements.add(max);
                if (max == arr[i - windowSize])
                    deque.pollFirst();
            }

            while(!deque.isEmpty() && deque.peekLast() < arr[i]){
                deque.pollLast();
            }
            deque.addLast(arr[i]);
        }
        if (!deque.isEmpty() && arrSize >= windowSize)
            maxElements.add(deque.peekFirst());
        return maxElements;
    }




}
