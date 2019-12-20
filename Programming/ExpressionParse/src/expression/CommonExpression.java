package expression;

public interface CommonExpression extends DoubleExpression, Expression, TripleExpression {
    int getPriority();
    String getOperator();
    boolean isNotCommutative();
}
