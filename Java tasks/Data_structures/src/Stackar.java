import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Stackar {
    public static void main(String[] args) {
        String check = "abcd";
        String result = reverse(check);
        System.out.println(result);

    }

    public static boolean checkString(String text){
        int sizeStack = 0;
        for (int i = 0; i < text.length(); i++){
            String symbol = text.substring(i, i + 1);
            if (symbol.equals("("))
                sizeStack++;
            else
                sizeStack--;
            if (sizeStack < 0)
                return false;

        }
        return sizeStack == 0;
    }

    public static boolean checkMultiString(String txt){
        List<Integer> stack = new ArrayList<>();
        List<String> opened = new ArrayList<>();
        opened.add("(");
        opened.add("[");
        opened.add("{");
        List<String> closed = new ArrayList<>();
        closed.add(")");
        closed.add("]");
        closed.add("}");
        for (int i = 0; i < txt.length(); i++){
            String symbol = txt.substring(i, i + 1);
            if (opened.contains(symbol))
                stack.add(opened.indexOf(symbol));
            else if(closed.contains(symbol)){
                if (closed.indexOf(symbol) == stack.get(stack.size() - 1))
                    stack.remove(stack.size() - 1);
                else
                    return false;
            }
        }
        if (stack.size() == 0)
            return true;
        else
            return false;
    }

    public static Stack<Integer> stackSort(Stack<Integer> stack){
        Stack<Integer> sortedStack = new Stack<>();
        while(!stack.isEmpty()){
            int stackLastElement = stack.pop();
            while (!sortedStack.isEmpty() && sortedStack.peek() > stackLastElement){
                stack.push(sortedStack.pop());
            }
            sortedStack.push(stackLastElement);
        }
        return sortedStack;
    }

    public static String reverse(String txt){
        Stack<Character> stack = new Stack<>();
        StringBuilder reverse = new StringBuilder();
        for (int i = 0 ; i < txt.length(); i++){
            char symbol = txt.charAt(i);
            stack.push(symbol);
        }
        for (int j = 0; j < txt.length(); j++){
            reverse.append(stack.pop());
        }
        return reverse.toString();
    }
}
