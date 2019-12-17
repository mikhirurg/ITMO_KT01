package expression;

public class Subtract extends BinaryOperation {
    Subtract(GenericExpression left, GenericExpression right) {
        super(left, right, '-');
    }

    @Override
    public int evaluate(int x) {
        return super.left.evaluate(x) - super.right.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return super.left.evaluate(x) - super.right.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return super.left.evaluate(x,y,z) - super.right.evaluate(x,y,z);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean isCommutative() {
        return false;
    }

}
