package expression;

public class Digits extends UnaryOperation {
    public Digits(BaseExpression operand) {
        super(operand, "digits");
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
        int value = operand.evaluate(x,y,z);
        String str =  Integer.toString(value);
        int res = 0;
        int pos = 0;
        if (str.charAt(0) == '-') {
            pos++;
        }
        while (pos < str.length()) {
            res += Integer.parseInt(String.valueOf(str.charAt(pos++)));
        }
        return res;
    }
}
