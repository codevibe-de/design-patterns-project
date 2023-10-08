import org.junit.jupiter.api.Test;
import parser.ExpressionResultParser;
import parser.ExpressionResultParserImpl;
import parser.ParserException;
import scanner.Scanner;
import scanner.ScannerImpl;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionResultParserTest {

    @Test
    public void parseEmptyInput() {
        final Scanner scanner = new ScannerImpl(new StringReader("  "));
        final ExpressionResultParser parser = new ExpressionResultParserImpl(scanner);
        assertThrows(ParserException.class, () -> parser.parse().evaluate());
    }

    @Test
    public void parseOneNumber() {
        final Scanner scanner = new ScannerImpl(new StringReader("  3.14 "));
        final ExpressionResultParser parser = new ExpressionResultParserImpl(scanner);
        assertEquals(3.14, parser.parse().evaluate());
        assertNull(scanner.current());
    }

    @Test
    public void parseTimesExpression() {
        final Scanner scanner = new ScannerImpl(new StringReader("  2 * 3.0 * 4 "));
        final ExpressionResultParser parser = new ExpressionResultParserImpl(scanner);
        assertEquals(24.0, parser.parse().evaluate());
        assertNull(scanner.current());
    }

    @Test
    public void parseMultiplicativeExpression() {
        final Scanner scanner = new ScannerImpl(new StringReader("  6 / 3.0 * 4 "));
        final ExpressionResultParser parser = new ExpressionResultParserImpl(scanner);
        assertEquals(8.0, parser.parse().evaluate());
        assertNull(scanner.current());
    }

    @Test
    public void parseAdditiveExpression() {
        final Scanner scanner = new ScannerImpl(new StringReader("  6 + 3.0 * 4 "));
        final ExpressionResultParser parser = new ExpressionResultParserImpl(scanner);
        assertEquals(18.0, parser.parse().evaluate());
        assertNull(scanner.current());
    }

    @Test
    public void parseNestedExpression() {
        final Scanner scanner = new ScannerImpl(new StringReader("  (6 + 3.0) * 4 "));
        final ExpressionResultParser parser = new ExpressionResultParserImpl(scanner);
        assertEquals(36.0, parser.parse().evaluate());
        assertNull(scanner.current());
    }

    @Test
    public void parseUnaryExpression() {
        final Scanner scanner = new ScannerImpl(new StringReader("  (+6 + 3.0) * -4 "));
        final ExpressionResultParser parser = new ExpressionResultParserImpl(scanner);
        assertEquals(-36.0, parser.parse().evaluate());
        assertNull(scanner.current());
    }
}
