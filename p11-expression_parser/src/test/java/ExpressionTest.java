import expressions.BinaryExpression;
import expressions.Expression;
import expressions.NumberExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionTest {

    @Test
    public void test() {
        final Expression e1 = new NumberExpression(40);
        final Expression e2 = new NumberExpression(2);
        final Expression e = new BinaryExpression(BinaryExpression.PLUS, e1, e2);
        assertEquals(42, 0, e.evaluate());
    }
}
