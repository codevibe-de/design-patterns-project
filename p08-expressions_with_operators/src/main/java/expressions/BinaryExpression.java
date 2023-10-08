package expressions;

import java.util.function.DoubleBinaryOperator;

public class BinaryExpression extends Expression {

    protected final DoubleBinaryOperator operator;
    protected final Expression left;
    protected final Expression right;

    public BinaryExpression(DoubleBinaryOperator operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate() {
        return this.operator.applyAsDouble(this.left.evaluate(), this.right.evaluate());
    }

    public static DoubleBinaryOperator PLUS = (v0, v1) -> v0 + v1;
    public static DoubleBinaryOperator MINUS = (v0, v1) -> v0 - v1;
    public static DoubleBinaryOperator TIMES = (v0, v1) -> v0 * v1;
    public static DoubleBinaryOperator DIV = (v0, v1) -> v0 / v1;

}
