import org.junit.jupiter.api.Test;
import scanner.*;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

public class ScannerTest {

    @Test
    public void testEmptyInput() {
        final Scanner s = new ScannerImpl(new StringReader(" "));
        s.next();
        assertNull(s.current());
    }

    @Test
    public void testNumbers() {
        final Scanner s = new ScannerImpl(new StringReader("42 3.14   77 "));
        s.next();
        assertEquals(new NumberSymbol(42), s.current());
        s.next();
        assertEquals(new NumberSymbol(3.14), s.current());
        s.next();
        assertEquals(new NumberSymbol(77), s.current());
        s.next();
        assertNull(s.current());
    }

    @Test
    public void testNumbersSame() {
        final Scanner s = new ScannerImpl(new StringReader("42 3.14   77 "));
        s.next();
        assertSame(s.numberSymbolOf(42), s.current());
        s.next();
        assertSame(s.numberSymbolOf(3.14), s.current());
        s.next();
        assertSame(s.numberSymbolOf(77), s.current());
        s.next();
        assertNull(s.current());
    }

    @Test
    public void testIdentifiers() {
        final Scanner s = new ScannerImpl(new StringReader("hello Hello a123"));
        s.next();
        assertEquals(new IdentifierSymbol("hello"), s.current());
        s.next();
        assertEquals(new IdentifierSymbol("Hello"), s.current());
        s.next();
        assertEquals(new IdentifierSymbol("a123"), s.current());
        s.next();
        assertNull(s.current());
    }

    @Test
    public void testIdentifiersSame() {
        final Scanner s = new ScannerImpl(new StringReader("hello Hello a123"));
        s.next();
        assertSame(s.identifierSymbolOf("hello"), s.current());
        s.next();
        assertSame(s.identifierSymbolOf("Hello"), s.current());
        s.next();
        assertSame(s.identifierSymbolOf("a123"), s.current());
        s.next();
        assertNull(s.current());
    }

    @Test
    public void testSpecials() {
        final Scanner s = new ScannerImpl(new StringReader("/* +-"));
        s.next();
        assertEquals(SpecialSymbol.DIV, s.current());
        s.next();
        assertEquals(SpecialSymbol.TIMES, s.current());
        s.next();
        assertEquals(SpecialSymbol.PLUS, s.current());
        s.next();
        assertEquals(SpecialSymbol.MINUS, s.current());
        s.next();
        assertNull(s.current());
    }

    @Test
    public void testNumberAndSpecials() {
        final Scanner s = new ScannerImpl(new StringReader("/123* +456-"));
        s.next();
        assertEquals(SpecialSymbol.DIV, s.current());
        s.next();
        assertEquals(new NumberSymbol(123), s.current());
        s.next();
        assertEquals(SpecialSymbol.TIMES, s.current());
        s.next();
        assertEquals(SpecialSymbol.PLUS, s.current());
        s.next();
        assertEquals(new NumberSymbol(456), s.current());
        s.next();
        assertEquals(SpecialSymbol.MINUS, s.current());
        s.next();
        assertNull(s.current());
    }

    @Test
    public void testExceptionLetterAfterNumber() {
        final Scanner s = new ScannerImpl(new StringReader("1a"));
        assertThrows(ScannerException.class, () -> s.next());
    }

    @Test
    public void testExceptionIllegalSpecial() {
        final Scanner s = new ScannerImpl(new StringReader("#"));
        assertThrows(ScannerException.class, () -> s.next());
    }
}
