package expressions;

public class BinaryPlusExpression extends BinaryExpression {

    public BinaryPlusExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double evaluate() {
        return this.left.evaluate() + this.right.evaluate();
    }
}
