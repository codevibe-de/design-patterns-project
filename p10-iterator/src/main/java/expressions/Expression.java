package expressions;

import java.util.Iterator;
import java.util.Stack;
import java.util.function.BiConsumer;

public abstract class Expression implements Iterable<Expression> {

    public abstract double evaluate();

    public abstract int getChildCount();

    public abstract Expression getChild(int index);

    // internal ITERATOR pattern
    public final void traverse(int indent, BiConsumer<Integer, Expression> consumer) {
        consumer.accept(indent, this);
        for (int i = 0; i < this.getChildCount(); i++) {
            this.getChild(i).traverse(indent + 1, consumer);
        }
    }

    // external ITERATOR pattern
    public Iterator<Expression> iterator() {
        return new ExpressionIterator(this);
    }
}

class ExpressionIterator implements Iterator<Expression> {

    Stack<Expression> nextExpressions = new Stack<>();

    public ExpressionIterator(Expression rootExpr) {
        nextExpressions.push(rootExpr);
    }

    @Override
    public boolean hasNext() {
        return !nextExpressions.isEmpty();
    }

    @Override
    public Expression next() {
        var nextExpr = nextExpressions.pop();
        for (int n = nextExpr.getChildCount() - 1; n >= 0; n--) {
            nextExpressions.push(nextExpr.getChild(n));
        }
        return nextExpr;
    }
}
