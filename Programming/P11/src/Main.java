import expression.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ClassExpression exp = new Add(
                new Variable("x"), new Variable("x")
        );
        System.out.println(exp.toString());
    }
}
