package expressions;

public class NumberExpression extends Expression {

    private final double value;

    public NumberExpression(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return this.value;
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public Expression getChild(int index) {
        throw new RuntimeException();
    }

}
