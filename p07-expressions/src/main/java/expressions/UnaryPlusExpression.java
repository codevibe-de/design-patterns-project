package expressions;

public class UnaryPlusExpression extends UnaryExpression {

    public UnaryPlusExpression(Expression inner) {
        super(inner);
    }

    @Override
    public double evaluate() {
        return +this.inner.evaluate();
    }
}
