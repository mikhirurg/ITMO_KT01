package expression;

public class Square extends UnaryOperation {
    public Square(BaseExpression operand) {
        super(operand, "square");
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
        return result*result;
    }
}
