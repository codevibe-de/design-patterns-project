package expressions;

public abstract class UnaryExpression extends Expression {

    protected final Expression inner;

    public UnaryExpression(Expression inner) {
        this.inner = inner;
    }
}
