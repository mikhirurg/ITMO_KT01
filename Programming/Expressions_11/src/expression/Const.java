package expression;

public class Const implements GenericExpression {
    boolean isIntVal;
    int intValue;
    double doubleValue;
    Const(int intValue) {
        this.intValue = intValue;
        this.doubleValue = intValue;
        isIntVal = true;
    }

    Const(double doubleValue) {
        this.doubleValue = doubleValue;
        this.intValue = (int) doubleValue;
        isIntVal = false;
    }

    @Override
    public int evaluate(int x) {
        return intValue;
    }

    @Override
    public double evaluate(double x) {
        return doubleValue;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return intValue;
    }

    @Override
    public String toString() {
        return isIntVal ? String.valueOf(intValue) : String.valueOf(doubleValue);
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public char getOperator() {
        return ' ';
    }

    @Override
    public boolean isCommutative() {
        return false;
    }


    @Override
    public int hashCode() {
        if (isIntVal) {
            return intValue;
        } else {
            return ((Double)doubleValue).hashCode();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }
}
