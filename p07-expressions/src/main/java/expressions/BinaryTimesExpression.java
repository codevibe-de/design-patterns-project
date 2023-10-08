package expressions;

public class BinaryTimesExpression extends BinaryExpression {

    public BinaryTimesExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double evaluate() {
        return this.left.evaluate() * this.right.evaluate();
    }
}
