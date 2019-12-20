package expression;

public class UMin extends UnaryOperation {
    public UMin(BaseExpression operand) {
        super(operand, "U-");
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isNotCommutative() {
        return true;
    }

    @Override
    protected int calculate(int x, int y, int z) {
        return -operand.evaluate(x,y,z);
    }

    @Override
    public String toMiniString() {
        String s = operand.toMiniString();
        return  getOperator() +
                (operand.getOperator().equals("const") ||
                        operand.getOperator().equals("variable")
                        ? s : "(" + s + ")");
    }
}
