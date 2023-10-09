package scanner;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Scanner {

    private final FlyweightFactory flyweightFactory = new FlyweightFactory();

    private final Reader reader;

    private int currentChar;

    public Scanner(Reader reader) {
        this.reader = reader;
        this.readNextChar();
    }

    public Symbol readSymbol() {
        final Object data;
        this.skipWhitespace();
        if (this.currentChar == -1) {
            data = null;
        } else if (Character.isDigit(this.currentChar)) {
            data = this.readNumber();
        } else if (Character.isJavaIdentifierStart((char) this.currentChar)) {
            data = this.readIdentifier();
        } else if ("+-*/()".indexOf(this.currentChar) >= 0) {
            data = (char) this.currentChar;
            this.readNextChar();
        } else {
            throw new ScannerException("illegal special symbol: " + (char) this.currentChar);
        }
        return flyweightFactory.getSymbol(data);
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

    private class FlyweightFactory {
        private final Map<Object, Symbol> symbols = new HashMap<>();

        public Symbol getSymbol(Object data) {
            return this.symbols.computeIfAbsent(data, v -> Symbol.of(data));
        }
    }
}
