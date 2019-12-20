package expression;

public class Abs extends UnaryOperation {
    public Abs(BaseExpression operand) {
        super(operand, "abs");
    }
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isNotCommutative() {
        return false;
    }
    @Override
    protected int calculate(int x, int y, int z) {
        int result = operand.evaluate(x,y,z);
        return result > 0 ? result : -result;
    }
}
