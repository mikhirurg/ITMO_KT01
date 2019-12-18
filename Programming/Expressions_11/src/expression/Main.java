package expression;

public class Main {
    public static void main(String[] args) {
        Expression e = new Const(10.0);
        System.out.println(e.evaluate(0));
    }
}
