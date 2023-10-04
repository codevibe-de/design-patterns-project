package p01.scanner;

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
        assertEquals(Double.valueOf(42), s.readSymbol());
        assertEquals(Double.valueOf(3.14), s.readSymbol());
        assertEquals(Double.valueOf(77), s.readSymbol());
        assertNull(s.readSymbol());
    }

    @Test
    public void testIdentifiers() {
        final Scanner s = new Scanner(new StringReader("hello Hello a123"));
        assertEquals("hello", s.readSymbol());
        assertEquals("Hello", s.readSymbol());
        assertEquals("a123", s.readSymbol());
        assertNull(s.readSymbol());
    }

    @Test
    public void testSpecials() {
        final Scanner s = new Scanner(new StringReader("/* +-"));
        assertEquals(Character.valueOf('/'), s.readSymbol());
        assertEquals(Character.valueOf('*'), s.readSymbol());
        assertEquals(Character.valueOf('+'), s.readSymbol());
        assertEquals(Character.valueOf('-'), s.readSymbol());
        assertNull(s.readSymbol());
    }

    @Test
    public void testNumberAndSpecials() {
        final Scanner s = new Scanner(new StringReader("/123* +456-"));
        assertEquals(Character.valueOf('/'), s.readSymbol());
        assertEquals(Double.valueOf(123), s.readSymbol());
        assertEquals(Character.valueOf('*'), s.readSymbol());
        assertEquals(Character.valueOf('+'), s.readSymbol());
        assertEquals(Double.valueOf(456), s.readSymbol());
        assertEquals(Character.valueOf('-'), s.readSymbol());
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
