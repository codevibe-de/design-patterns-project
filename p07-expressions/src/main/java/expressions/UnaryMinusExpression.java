package expressions;

public class UnaryMinusExpression extends UnaryExpression {

    public UnaryMinusExpression(Expression inner) {
        super(inner);
    }

    @Override
    public double evaluate() {
        return -this.inner.evaluate();
    }
}
