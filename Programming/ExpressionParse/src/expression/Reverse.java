package expression;

public class Reverse extends UnaryOperation {
    public Reverse(BaseExpression operand) {
        super(operand, "reverse");
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
        StringBuilder res = new StringBuilder();
        int pos = 0;
        int start = 0;
        if (str.charAt(0) == '-') {
            res.append('-');
            pos++;
            start = 1;
        }
        while (pos < str.length()) {
            res.insert(start, str.charAt(pos++));
        }

        return (int)Long.parseLong(res.toString());
    }
}
