package expression;

public class Divide extends BinaryOperation {
    Divide(GenericExpression left, GenericExpression right) {
        super(left, right, '/');
    }

    @Override
    public int evaluate(int x) {
        return super.left.evaluate(x) / super.right.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return super.left.evaluate(x) / super.right.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return super.left.evaluate(x,y,z) / super.right.evaluate(x,y,z);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isCommutative() {
        return false;
    }

}
