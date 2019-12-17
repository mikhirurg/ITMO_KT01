package expression;

public interface GenericExpression extends DoubleExpression, Expression, TripleExpression {
    int getPriority();
    char getOperator();
    boolean isCommutative();
}
