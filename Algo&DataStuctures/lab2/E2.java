import java.util.Scanner;
import java.util.StringTokenizer;

public class E2 {
    public static class stack {
        class listElement {
            listElement next;
            String data;
        }
        listElement top = null;
        void push(String x) {
            if (top == null) {
                listElement e = new listElement();
                e.data = x;
                e.next = top;
                top = e;
            } else {
                listElement e = new listElement();
                e.data = x;
                e.next = top;
                top = e;
            }
        }

        String pop() {
            if (top != null) {
                String data = top.data;
                top = top.next;
                return data;
            }
            return null;
        }
        listElement getTop(){
            return top;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        StringTokenizer tok = new StringTokenizer(s);
        stack stk = new stack();
        while (tok.hasMoreTokens()){
            String tmp = tok.nextToken();
            if (tmp.charAt(0) == '+' || tmp.charAt(0) == '*' || tmp.charAt(0) == '-'){
                char oper = tmp.charAt(0);
                int b = Integer.parseInt(stk.pop());
                int a = Integer.parseInt(stk.pop());
                int c = 0;
                switch (oper){
                    case '+':c = a+b;
                    break;
                    case '-':c = a-b;
                    break;
                    case '*':c = a*b;
                }
                stk.push(String.valueOf(c));
            } else {
                stk.push(tmp);
            }
        }
        System.out.println(stk.pop());
    }
}
