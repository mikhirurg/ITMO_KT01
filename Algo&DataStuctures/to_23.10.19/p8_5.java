import java.util.Scanner;
import java.util.StringTokenizer;

public class p8_5 {
    static queue exp = new queue();
    static stack zn = new stack();
    static void toInfix(queue q) {
        if (q.getHead()!=null) {
            String str = q.remove();
            if (str.charAt(0) == '+' || str.charAt(0) == '-' || str.charAt(0) == '*' || str.charAt(0) == '/') {
                zn.push(str);
                toInfix(q);
                toInfix(q);
                if (zn.getTop()!=null) {
                    exp.add(zn.pop());
                }
            } else {
                exp.add(str);
            }
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        queue q = new queue();
        StringTokenizer tok = new StringTokenizer(input);
        while (tok.hasMoreElements()) {
            q.add(tok.nextToken());
        }
        toInfix(q);
        while (exp.getHead()!=null) {
            System.out.print(exp.remove()+" ");
        }
    }
}
