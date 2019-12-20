package expression;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Invalid number of arguments");
            System.exit(1);
        }
        int x = 0;
        try {
            x = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid argument");
            System.exit(1);
        }
        BaseExpression exp = new Add(
                new Subtract(
                        new Multiply(
                                new Variable("x"),
                                new Variable("x")),
                        new Multiply(
                                new Const(2),
                                new Variable("x"))),
                new Const(1));
        System.out.println(exp.evaluate(x));
    }
}
