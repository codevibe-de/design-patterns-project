package scanner;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Scanner {

    final FlyweightFactory flyweightFactory = new FlyweightFactory();

    private final Reader reader;

    private int currentChar;

    public Scanner(Reader reader) {
        this.reader = reader;
        this.readNextChar();
    }

    public Symbol readSymbol() {
        this.skipWhitespace();
        if (this.currentChar == -1) {
            return null;
        } else if (Character.isDigit(this.currentChar)) {
            double data = this.readNumber();
            return flyweightFactory.getSymbol(data);
        } else if (Character.isJavaIdentifierStart((char) this.currentChar)) {
            String name = this.readIdentifier();
            return flyweightFactory.getSymbol(name);
        } else if ("+-*/()".indexOf(this.currentChar) >= 0) {
            char ch = (char) this.currentChar;
            this.readNextChar();
            return flyweightFactory.getSymbol(ch);
        } else {
            throw new ScannerException("unhandled current-char: " + (char) this.currentChar);
        }
    }

    private double readNumber() {
        final StringBuilder buf = new StringBuilder();
        buf.append((char) this.currentChar);
        this.readNextChar();
        while (Character.isDigit(this.currentChar)) {
            buf.append((char) this.currentChar);
            this.readNextChar();
        }
        if (this.currentChar == '.') {
            buf.append('.');
            this.readNextChar();
            while (Character.isDigit(this.currentChar)) {
                buf.append((char) this.currentChar);
                this.readNextChar();
            }
        }
        if (Character.isLetter(this.currentChar)) {
            throw new ScannerException("a number mustn't end with a letter");
        }
        return Double.valueOf(buf.toString());
    }

    private String readIdentifier() {
        final StringBuilder buf = new StringBuilder();
        buf.append((char) this.currentChar);
        this.readNextChar();
        while (Character.isJavaIdentifierPart((char) this.currentChar)) {
            buf.append((char) this.currentChar);
            this.readNextChar();
        }
        return buf.toString();
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(this.currentChar)) {
            this.readNextChar();
        }
    }

    private void readNextChar() {
        try {
            this.currentChar = this.reader.read();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    //
    // --- inner classes ---
    //

    static class FlyweightFactory {

        private final Map<Object, Symbol> symbols = new HashMap<>();

        Symbol getSymbol(final Object data) {
            final Object finalData = (data instanceof Number number) ? number.doubleValue() : data;
            return this.symbols.computeIfAbsent(finalData, v -> Symbol.of(finalData));
        }

    }
}
