import expressions.BinaryExpression;
import expressions.Expression;
import expressions.NumberExpression;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionTest {

    @Test
    public void test() {
        final Expression e1 = new NumberExpression(40);
        final Expression e2 = new NumberExpression(2);
        final Expression e = new BinaryExpression(BinaryExpression.PLUS, e1, e2);

        assertEquals(42, 0, e.evaluate());
        assertEquals(0, e1.getChildCount());
        assertThrows(RuntimeException.class, () -> e1.getChild(0));
        assertEquals(0, e2.getChildCount());
        assertThrows(RuntimeException.class, () -> e2.getChild(0));
        assertEquals(2, e.getChildCount());
        assertSame(e1, e.getChild(0));
        assertSame(e2, e.getChild(1));
    }


    @Test
    public void demo1() {
        final Expression e1 = new NumberExpression(40);
        final Expression e2 = new NumberExpression(2);
        final Expression rootExp = new BinaryExpression(BinaryExpression.PLUS, e1, e2);
        this.traverse(0, rootExp);
    }

    private void traverse(int indent, Expression e) {
        for (int i = 0; i < indent; i++)
            System.out.print("    ");
        System.out.println(e);
        for (int i = 0; i < e.getChildCount(); i++)
            this.traverse(indent + 1, e.getChild(i));
    }


    @Test
    public void demo2() {
        final Expression e1 = new NumberExpression(40);
        final Expression e2 = new NumberExpression(2);
        final Expression e = new BinaryExpression(BinaryExpression.PLUS, e1, e2);
        e.traverse(0, (indent, expr) -> {
            for (int i = 0; i < indent; i++)
                System.out.print("    ");
            System.out.println(expr);
        });
    }

    @Test
    void iterator() {
        // given: 40 + 2 * 3
        final Expression e1 = new NumberExpression(40);
        final Expression e2a = new NumberExpression(2);
        final Expression e2b = new NumberExpression(3);
        final Expression e2 = new BinaryExpression(BinaryExpression.TIMES, e2a, e2b);
        final Expression rootExp = new BinaryExpression(BinaryExpression.PLUS, e1, e2);

        // then
        Assertions.assertThat(rootExp).containsExactly(rootExp, e1, e2, e2a, e2b);
    }
}
