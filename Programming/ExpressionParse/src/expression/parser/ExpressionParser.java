package expression.parser;

import expression.*;

import java.util.Stack;

public class ExpressionParser implements Parser {
    Lexer lexer;
    Stack<BaseExpression> stack;

    public ExpressionParser() {
        stack = new Stack<>();
    }

    public TripleExpression parse(String expression) {
        lexer = new Lexer(expression);
        exprs();
        return stack.pop();
    }

    private void exprs() {
        expr();
        exprs1();
    }

    private void exprs1() {
        if (lexer.expect("<<")) {
            expr();
            BaseExpression arg2 = stack.pop();
            BaseExpression arg1 = stack.pop();
            stack.push(new LShift(arg1, arg2));
            exprs1();
            return;
        }
        if (lexer.expect(">>")) {
            expr();
            BaseExpression arg2 = stack.pop();
            BaseExpression arg1 = stack.pop();
            stack.push(new RShift(arg1, arg2));
            exprs1();
        }
    }

    private void expr() {
        term();
        expr1();
    }

    private void expr1() {
        if (!lexer.hasNext()) {
            return;
        }
        if (lexer.expect("+")) {
            term();
            BaseExpression arg2 = stack.pop();
            BaseExpression arg1 = stack.pop();
            stack.push(new Add(arg1, arg2));
            expr1();
            return;
        }
        if (lexer.expect("-")) {
            term();
            BaseExpression arg2 = stack.pop();
            BaseExpression arg1 = stack.pop();
            stack.push(new Subtract(arg1, arg2));
            expr1();
        }
    }

    private void term() {
        factor();
        term1();
    }

    private void term1() {
        if (!lexer.hasNext()) {
            return;
        }

        if (lexer.expect("*")) {
            factor();
            BaseExpression arg2 = stack.pop();
            BaseExpression arg1 = stack.pop();
            stack.push(new Multiply(arg1, arg2));
            term1();
            return;
        }

        if (lexer.expect("/")) {
            factor();
            BaseExpression arg2 = stack.pop();
            BaseExpression arg1 = stack.pop();
            stack.push(new Divide(arg1, arg2));
            term1();
        }
    }

    private void factor() {

        Stack<String> unaryOps = new Stack<>();
        boolean hasUnary = true;
        while (lexer.hasNext() && hasUnary) {
            hasUnary = false;

            if (lexer.expect("reverse")) {
                unaryOps.push("reverse");
                hasUnary = true;
            }

            if (lexer.expect("digits")) {
                unaryOps.push("digits");
                hasUnary = true;
            }

            if (lexer.expect("-")) {
                unaryOps.push("-");
                hasUnary = true;
            }

            if (lexer.expect("abs")) {
                unaryOps.add("abs");
                hasUnary = true;
            }

            if (lexer.expect("square")) {
                unaryOps.add("square");
                hasUnary = true;
            }
        }

        if (lexer.expect("(")) {
            exprs();
            lexer.expect(")");
            processUnary(unaryOps);
            return;
        }


        if (lexer.expectNumber()) {
            long value = lexer.getValue();
            boolean notPatched = true;
            stack.push(new Const((int)value));

            if (unaryOps.size() > 0) {
                while (!unaryOps.empty()) {
                    String op = unaryOps.pop();
                    switch (op) {
                        case "reverse":
                            stack.push(new Reverse(stack.pop()));
                            break;
                        case "digits":
                            stack.push(new Digits(stack.pop()));
                            break;
                        case "-":
                           if (stack.peek().getOperator().equals("const") && notPatched) {
                                stack.pop();
                                stack.push(new Const((int)(-value)));
                                notPatched = false;
                            } else {
                                stack.push(new UMin(stack.pop()));
                            }
                            break;
                        case "abs":
                            stack.push(new Abs(stack.pop()));
                            break;
                        case "square":
                            stack.push(new Square(stack.pop()));
                            break;
                    }
                }
            }
            return;
        }

        if (lexer.expectVar()) {
            stack.push(new Variable(lexer.getVariable()));

            processUnary(unaryOps);
        }
    }

    private void processUnary(Stack<String> unaryOps) {
        if (unaryOps.size() > 0) {
            while (!unaryOps.empty()) {
                String op = unaryOps.pop();
                switch (op) {
                    case "reverse":
                        stack.push(new Reverse(stack.pop()));
                        break;
                    case "digits":
                        stack.push(new Digits(stack.pop()));
                        break;
                    case "-":
                        stack.push(new UMin(stack.pop()));
                        break;
                    case "abs":
                        stack.push(new Abs(stack.pop()));
                        break;
                    case "square":
                        stack.push(new Square(stack.pop()));
                        break;
                }
            }
        }
    }


    public static void main(String[] args) {
        Parser p = new ExpressionParser();
        TripleExpression e = p.parse("abs -5");
        System.out.println(e.toMiniString());
        System.out.println(e.toString());
        System.out.println(e.evaluate(-101971866, 1641629423, 871165933));

    }
}
