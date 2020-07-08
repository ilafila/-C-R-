import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class QueueArr {
    public static void main(String[] args) {

        int[] test = new int[] {6,1,2,3,4,5};
        int kk = 3;
        generateBinNumbers(10);
    }

     public static List<Integer> reverseFirsKelements(int[] arr,int k){
        int kCopy = k;
         ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
         for (int i = 0; i < arr.length; i++){
             queue.offer(arr[i]);
         }

         Stack<Integer> stack = new Stack<>();
         while (kCopy != 0){
             stack.push(queue.poll());
             kCopy = kCopy - 1;
         }
         int queueSize = queue.size();

         while (!stack.isEmpty()){
             queue.offer(stack.pop());
         }

         for (int i = 0; i < queueSize; i++){
             queue.offer(queue.poll());
         }

         List<Integer> result = new ArrayList<>();
         while (!queue.isEmpty()){
             result.add(queue.poll());
         }
         return result;
     }

     public  static void generateBinNumbers(int num){
         ArrayDeque<String> queue = new ArrayDeque<>();
         queue.offer("1");
         StringBuilder str1 = new StringBuilder();
         StringBuilder str2 = new StringBuilder();

         for (int i = 0; i < num; i++){

             String s1 = queue.peek();
             String s2 = s1;

             str1.append(s1);
             str2.append(s2);
             System.out.println(queue.poll() + " ");
             queue.offer(str1.append("0").toString());
             queue.offer(str2.append("1").toString());
             str1.delete(0, str1.length());
             str2.delete(0, str2.length());
         }
     }





}
