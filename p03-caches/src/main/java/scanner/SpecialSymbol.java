package scanner;

import java.util.Arrays;

public enum SpecialSymbol implements Symbol {

    PLUS('+'),
    MINUS('-'),
    TIMES('*'),
    DIV('/'),
    OPEN('('),
    CLOSE(')');

    private final char ch;

    SpecialSymbol(char ch) {
        this.ch = ch;
    }

    public char ch() {
        return this.ch;
    }

    // STATIC FACTORY METHOD pattern
    public static Symbol forChar(char ch) {
        // find enum represented by given character
        return Arrays.stream(SpecialSymbol.values())
                .filter(e -> e.ch() == ch)
                .findFirst()
                .orElseThrow(() -> new ScannerException("No symbol for " + ch));
    }
}
