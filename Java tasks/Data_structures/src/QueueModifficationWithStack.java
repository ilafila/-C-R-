import java.util.Stack;

public class QueueModifficationWithStack {

    private Stack<Integer> stackPush = new Stack<>();
    private Stack<Integer> stackPop = new Stack<>();


    public void pushBack(int data){
        stackPush.push(data);
    }

    public void popFront(){
        while (!stackPush.isEmpty())
            stackPop.push(stackPush.pop());
    }

    public void DequeueModifficated(){
        popFront();
        stackPop.pop();
    }

    public void EnqueueModifficated(int data){
        pushBack(data);
    }

    public int TopModifficated(){
        popFront();
        return stackPop.peek();
    }

    public static void main(String[] args) {
        QueueModifficationWithStack queueMod = new QueueModifficationWithStack();
        queueMod.EnqueueModifficated(1);
        queueMod.EnqueueModifficated(2);
        queueMod.EnqueueModifficated(3);
        queueMod.EnqueueModifficated(4);
        queueMod.DequeueModifficated();
        queueMod.DequeueModifficated();
        queueMod.DequeueModifficated();
        int test = queueMod.TopModifficated();
        System.out.println(test);
    }

}
