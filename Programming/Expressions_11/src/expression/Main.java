package expression;

public class Main {
    public static void main(String[] args) {
        GenericExpression e = new Divide(new Multiply(new Const(10.0), new Const(20.0)), new Variable("x"));
        e.evaluate(11.0);
        System.out.println(e.evaluate(11.0));
    }
}
