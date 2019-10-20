import java.util.Scanner;
import java.util.StringTokenizer;

public class p8_6 {
    static boolean isInt(String x) {
        try {
            Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        StringTokenizer tok = new StringTokenizer(input);
        queue expr = new queue();
        stack oper = new stack();
        stack eval = new stack();

        while (tok.hasMoreElements()) {
            String x = tok.nextToken();
            if (x.charAt(0) == ')') {
                expr.add(oper.pop());
                if (oper.top.data.charAt(0) == '(') oper.pop();
            } else {
                if (!isInt(x)) {
                    oper.push(x);
                } else {
                    expr.add(x);
                }
            }
        }
        while (expr.getHead()!=null) {
            System.out.print(expr.remove()+" ");
        }

    }
}
