package expressions;

import java.util.function.BiConsumer;

public abstract class Expression {

    public abstract double evaluate();

    public abstract int getChildCount();

    public abstract Expression getChild(int index);

    public final void traverse(int indent, BiConsumer<Integer, Expression> consumer) {
        consumer.accept(indent, this);
        for (int i = 0; i < this.getChildCount(); i++) {
            this.getChild(i).traverse(indent + 1, consumer);
        }
    }
}
