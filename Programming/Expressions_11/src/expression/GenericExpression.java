package expression;

public interface GenericExpression extends DoubleExpression, Expression, TripleExpression {
    int getPriority();
    String getOperator();
    boolean isCommutative();
    int getIntConstVal();
    double getDoubleConstVal();
    boolean isPreEval();
    boolean isConst();
    void PreEvaluated();
    void addResult(int x, int val);
    void addResult(double x, double val);
}
