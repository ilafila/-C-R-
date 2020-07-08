import java.util.Stack;

public class StackWithMinElement {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minElements = new Stack<>();


    public void push(int data){
        int min = data;

        if (!minElements.isEmpty() && minElements.peek() < data)
            min = minElements.peek();
        stack.push(data);
        minElements.push(min);
    }

    public void pop(){
        stack.pop();
        minElements.pop();
    }

    public int getMinElement(){
        return minElements.peek();
    }

    public static void main(String[] args) {
        StackWithMinElement test = new StackWithMinElement();
        test.push(3);
        test.push(2);
        test.push(4);
        test.push(1);
        test.push(9);

        System.out.println(test.getMinElement());
        test.pop();
        test.pop();
        System.out.println(test.getMinElement());

    }
}
