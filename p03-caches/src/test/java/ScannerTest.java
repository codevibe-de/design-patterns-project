import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import scanner.*;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

public class ScannerTest {

    @Test
    public void testEmptyInput() {
        final Scanner s = new Scanner(new StringReader(" "));
        assertNull(s.readSymbol());
    }

    @Test
    public void testNumbers() {
        final Scanner s = new Scanner(new StringReader("42 3.14   77 "));
        Assertions.assertEquals(new NumberSymbol(42), s.readSymbol());
        assertEquals(new NumberSymbol(3.14), s.readSymbol());
        assertEquals(new NumberSymbol(77), s.readSymbol());
        assertNull(s.readSymbol());
    }

    @Test
    public void testNumbersSame() {
        final Scanner s = new Scanner(new StringReader("42 3.14   77 "));
        assertSame(s.numberSymbolOf(42), s.readSymbol());
        assertSame(s.numberSymbolOf(3.14), s.readSymbol());
        assertSame(s.numberSymbolOf(77), s.readSymbol());
        assertNull(s.readSymbol());
    }

    @Test
    public void testIdentifiers() {
        final Scanner s = new Scanner(new StringReader("hello Hello a123"));
        Assertions.assertEquals(new IdentifierSymbol("hello"), s.readSymbol());
        assertEquals(new IdentifierSymbol("Hello"), s.readSymbol());
        assertEquals(new IdentifierSymbol("a123"), s.readSymbol());
        assertNull(s.readSymbol());
    }

    @Test
    public void testIdentifiersSame() {
        final Scanner s = new Scanner(new StringReader("hello Hello a123"));
        assertSame(s.identifierSymbolOf("hello"), s.readSymbol());
        assertSame(s.identifierSymbolOf("Hello"), s.readSymbol());
        assertSame(s.identifierSymbolOf("a123"), s.readSymbol());
        assertNull(s.readSymbol());
    }

    @Test
    public void testSpecials() {
        final Scanner s = new Scanner(new StringReader("/* +-"));
        Assertions.assertEquals(SpecialSymbol.DIV, s.readSymbol());
        assertEquals(SpecialSymbol.TIMES, s.readSymbol());
        assertEquals(SpecialSymbol.PLUS, s.readSymbol());
        assertEquals(SpecialSymbol.MINUS, s.readSymbol());
        assertNull(s.readSymbol());
    }

    @Test
    public void testNumberAndSpecials() {
        final Scanner s = new Scanner(new StringReader("/123* +456-"));
        assertEquals(SpecialSymbol.DIV, s.readSymbol());
        assertEquals(new NumberSymbol(123), s.readSymbol());
        assertEquals(SpecialSymbol.TIMES, s.readSymbol());
        assertEquals(SpecialSymbol.PLUS, s.readSymbol());
        assertEquals(new NumberSymbol(456), s.readSymbol());
        assertEquals(SpecialSymbol.MINUS, s.readSymbol());
        assertNull(s.readSymbol());
    }

    @Test
    public void testExceptionLetterAfterNumber() {
        final Scanner s = new Scanner(new StringReader("1a"));
        assertThrows(ScannerException.class, () -> s.readSymbol());
    }

    @Test
    public void testExceptionIllegalSpecial() {
        final Scanner s = new Scanner(new StringReader("#"));
        assertThrows(ScannerException.class, () -> s.readSymbol());
    }
}
