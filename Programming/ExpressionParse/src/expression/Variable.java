package expression;

public class Variable extends BaseExpression
        implements CommonExpression {
    private String variable;

    public Variable(String variable) {
        this.variable = variable;
        setConstExpr(false);
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (variable) {
            case "x": return x;
            case "y": return y;
            case "z": return z;
        }
        throw new IllegalArgumentException("Wrong variable: \""+variable+"\"!");
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String getOperator() {
        return "variable";
    }

    @Override
    public boolean isNotCommutative() {
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 0;
        for (char c : variable.toCharArray()) {
            hash += c*prime;
            prime*=prime;
        }
        return hash;
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
