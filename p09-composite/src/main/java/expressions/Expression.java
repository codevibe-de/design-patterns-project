package expressions;

public abstract class Expression {

    public abstract double evaluate();

    public abstract int getChildCount();

    public abstract Expression getChild(int index);

}
