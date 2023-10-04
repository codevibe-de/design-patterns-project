package scanner;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Scanner {

    private final Map<Double, NumberSymbol> numberSymbols = new HashMap<>();

    public NumberSymbol numberSymbolOf(double value) {
        return this.numberSymbols.computeIfAbsent(value, v -> new NumberSymbol(v));
    }

    private final Map<String, IdentifierSymbol> identifierSymbols = new HashMap<>();

    public IdentifierSymbol identifierSymbolOf(String name) {
        return this.identifierSymbols.computeIfAbsent(name, n -> new IdentifierSymbol(n));
    }

    private final Reader reader;

    private int currentChar;

    public Scanner(Reader reader) {
        this.reader = reader;
        this.readNextChar();
    }

    public Symbol readSymbol() {
        final Symbol result;
        this.skipWhitespace();
        if (this.currentChar == -1) {
            result = null;
        } else if (Character.isDigit(this.currentChar)) {
            result = this.numberSymbolOf(this.readNumber());
        } else if (Character.isJavaIdentifierStart((char) this.currentChar)) {
            result = this.identifierSymbolOf(this.readIdentifier());
        } else {
            final Symbol symbol = SpecialSymbol.forChar((char) this.currentChar);
            if (symbol == null) {
                throw new ScannerException("illegal special symbol: " + (char) this.currentChar);
            }
            this.readNextChar();
            result = symbol;
        }
        return result;
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
}
