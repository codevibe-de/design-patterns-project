package expressions;

import java.util.function.DoubleUnaryOperator;

public class UnaryExpression extends Expression {

    protected final DoubleUnaryOperator operator;
    protected final Expression inner;

    public UnaryExpression(DoubleUnaryOperator operator, Expression inner) {
        this.operator = operator;
        this.inner = inner;
    }

    @Override
    public double evaluate() {
        return this.operator.applyAsDouble(this.inner.evaluate());
    }

    public static DoubleUnaryOperator PLUS = v -> +v;
    public static DoubleUnaryOperator MINUS = v -> -v;

    @Override
    public int getChildCount() {
        return 1;
    }

    @Override
    public Expression getChild(int index) {
        if (index == 0) {
            return this.inner;
        }
        throw new RuntimeException();
    }

}
