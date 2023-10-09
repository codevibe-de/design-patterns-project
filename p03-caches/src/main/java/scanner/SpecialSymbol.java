package scanner;

import java.util.HashMap;
import java.util.Map;

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

    private static Map<Character, Symbol> symbols;

    // STATIC FACTORY METHOD pattern
    public static Symbol forChar(char ch) {
        if (symbols == null) {
            symbols = new HashMap<>();
            for (final SpecialSymbol symbol : values()) {
                symbols.put(symbol.ch, symbol);
            }
        }
        return symbols.get(ch);
    }
}
