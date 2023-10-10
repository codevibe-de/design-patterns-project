package scanner;

import java.io.IOException;
import java.io.Reader;

public class ScannerImpl implements Scanner {

    private final Reader reader;

    private int currentChar;

    private Symbol currentSymbol;

    public ScannerImpl(Reader reader) {
        this.reader = reader;
        this.readNextChar();
    }

    @Override
    public Symbol current() {
        return this.currentSymbol;
    }

    @Override
    public void next() {
        this.skipWhitespace();
        final Object data;
        if (this.currentChar == -1) {
            data = null;
        } else if (Character.isDigit(this.currentChar)) {
            data = this.readNumber();
        } else if (Character.isJavaIdentifierStart((char) this.currentChar)) {
            data = this.readIdentifier();
        } else {
            data = (char) this.currentChar;
            this.readNextChar();
        }
        this.currentSymbol = FlyweightFactory.INSTANCE.getSymbol(data);
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
