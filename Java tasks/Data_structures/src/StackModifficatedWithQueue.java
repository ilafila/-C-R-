import java.util.ArrayDeque;

public class StackModifficatedWithQueue {
    ArrayDeque<Integer> queue1 = new ArrayDeque<Integer>();
    ArrayDeque<Integer> queue2 = new ArrayDeque<Integer>();

    public void enqueue(int data){
        queue1.offer(data);
    }

    public int dequeue(){
        while(!(queue1.size() == 1)){
            queue2.offer(queue1.poll());
        }
        int res = queue1.poll();

        while(!(queue2.size() == 0)){
            queue1.offer(queue2.poll());
        }
        return res;
    }


    public static void main(String[] args) {
        StackModifficatedWithQueue check = new StackModifficatedWithQueue();
        check.enqueue(2);
        check.enqueue(7);
        check.enqueue(3);
        check.enqueue(9);
        check.enqueue(8);
        check.enqueue(6);
        int test = check.dequeue();
        System.out.println(test);
    }


}
