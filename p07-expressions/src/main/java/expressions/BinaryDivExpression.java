package expressions;

public class BinaryDivExpression extends BinaryExpression {

    public BinaryDivExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double evaluate() {
        return this.left.evaluate() * this.right.evaluate();
    }
}
