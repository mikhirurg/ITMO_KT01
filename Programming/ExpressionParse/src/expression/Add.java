package expression;

public class Add extends BinaryOperation {
    public Add(BaseExpression left, BaseExpression right) {
        super(left, right, "+");
    }

    @Override
    protected int calculate(int x) {
        return super.left.evaluate(x) + super.right.evaluate(x);
    }

    @Override
    protected double calculate(double x) {
        return super.left.evaluate(x) + super.right.evaluate(x);
    }

    @Override
    protected int calculate(int x, int y, int z) {
        return super.left.evaluate(x,y,z) + super.right.evaluate(x,y,z);
    }
    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean isNotCommutative() {
        return false;
    }

    @Override
    public String toMiniString() {
        return toMiniStringSimple();
    }

}
