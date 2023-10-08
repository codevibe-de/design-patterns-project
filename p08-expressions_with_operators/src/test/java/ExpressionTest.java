import expressions.BinaryExpression;
import expressions.Expression;
import expressions.NumberExpression;
import expressions.UnaryExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionTest {

    @Test
    void unaryExpression() {
        final Expression exp = new UnaryExpression(
                UnaryExpression.MINUS,
                new NumberExpression(1.23)
        );
        double result = exp.evaluate();
        assertEquals(-1.23, result, 0.0001);
    }

    @Test
    public void binaryExpression() {
        final Expression exp = new BinaryExpression(
                BinaryExpression.PLUS,
                new NumberExpression(40),
                new NumberExpression(2)
        );
        double result = exp.evaluate();
        assertEquals(42, result, 0.0001);
    }

}
