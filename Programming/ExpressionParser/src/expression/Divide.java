package expression;

public class Divide extends BinaryOperation {
    Divide(BaseExpression left, BaseExpression right) {
        super(left, right, '/');
    }


    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isNotCommutative() {
        return true;
    }

    @Override
    protected int calculate(int x) {
        return super.left.evaluate(x) / super.right.evaluate(x);
    }

    @Override
    protected double calculate(double x) {
        return super.left.evaluate(x) / super.right.evaluate(x);
    }

    @Override
    protected int calculate(int x, int y, int z) {
        return super.left.evaluate(x,y,z) / super.right.evaluate(x,y,z);
    }
}
