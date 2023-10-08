package expressions;

public class BinaryMinusExpression extends BinaryExpression {

    public BinaryMinusExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double evaluate() {
        return this.left.evaluate() - this.right.evaluate();
    }
}
