package scanner;

public interface Scanner {
    NumberSymbol numberSymbolOf(double value);

    IdentifierSymbol identifierSymbolOf(String name);

    Symbol current();

    void next();
}
