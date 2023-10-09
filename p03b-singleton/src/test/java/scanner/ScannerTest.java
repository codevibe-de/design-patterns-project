package scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        FlyweightFactory flyweightFactory = FlyweightFactory.INSTANCE;
        assertSame(flyweightFactory.getSymbol(42), s.readSymbol());
        assertSame(flyweightFactory.getSymbol(3.14), s.readSymbol());
        assertSame(flyweightFactory.getSymbol(77), s.readSymbol());
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
        FlyweightFactory flyweightFactory = FlyweightFactory.INSTANCE;
        assertSame(flyweightFactory.getSymbol("hello"), s.readSymbol());
        assertSame(flyweightFactory.getSymbol("Hello"), s.readSymbol());
        assertSame(flyweightFactory.getSymbol("a123"), s.readSymbol());
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
