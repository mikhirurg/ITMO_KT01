import java.util.Scanner;
import java.util.StringTokenizer;

public class p8_4 {
    static String[] test = new String[]{
            "( ( 8 + ( 2 * 5 ) ) / ( ( 1 + ( 3 * 2 ) ) - 4 ) )",
            "( 1 + 2 )"
    };

    static boolean isInt(String x) {
        try {
            Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static Double oper(String op, String a, String b) {
        switch (op.charAt(0)) {
            case '+':
                return Double.parseDouble(a) + Double.parseDouble(b);
            case '-':
                return Double.parseDouble(a) - Double.parseDouble(b);
            case '*':
                return Double.parseDouble(a) * Double.parseDouble(b);
            case '/':
                return Double.parseDouble(a) / Double.parseDouble(b);
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = test[0];
        queue exp = new queue();
        stack oper = new stack();
        stack eval = new stack();
        StringTokenizer tok = new StringTokenizer(input);

        while (tok.hasMoreElements()) {
            String x = tok.nextToken();
            if (x.charAt(0) == ')') {
                exp.add(oper.pop());
                if (oper.top.data.charAt(0) == '(') oper.pop();
            } else {
                if (!isInt(x)) {
                    oper.push(x);
                } else {
                    exp.add(x);
                }
            }
        }
        while (exp.getHead() != null) {
            String tmp = exp.remove();
            if (isInt(tmp)) {
                eval.push(tmp);
            } else {
                String a = eval.pop();
                String b = eval.pop();
                Double c = oper(tmp, b, a);
                eval.push(String.valueOf(c));
            }
        }

        System.out.println(eval.pop());
    }
}
