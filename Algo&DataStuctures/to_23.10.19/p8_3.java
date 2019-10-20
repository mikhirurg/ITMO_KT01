import java.util.Scanner;
import java.util.StringTokenizer;

public class p8_3 {
    static boolean isInt(String x){
        try{
            Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
    static Double oper(String op, String a, String b){
        switch (op.charAt(0)){
            case '+': return Double.parseDouble(a)+Double.parseDouble(b);
            case '-': return Double.parseDouble(a)-Double.parseDouble(b);
            case '*': return Double.parseDouble(a)*Double.parseDouble(b);
            case '/': return Double.parseDouble(a)/Double.parseDouble(b);
        }
        return -1.0;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        stack exp = new stack();
        stack help = new stack();
        StringTokenizer tok = new StringTokenizer(input);
        while (tok.hasMoreElements()){
            exp.push(tok.nextToken());
        }

        while (exp.top != null){
            String tmp = exp.pop();
            if (isInt(tmp)){
                help.push(tmp);
            } else {
                String a = help.pop();
                String b = help.pop();
                Double c = oper(tmp, a, b);
                help.push(String.valueOf(c));
            }
        }

        System.out.println(help.pop());

    }
}
