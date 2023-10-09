package scanner;

public interface Symbol {

    // STATIC FACTORY METHOD pattern
    static Symbol of(Object data) {
        if (data == null) {
            return null;
        } else if (data instanceof String name) {
            return new IdentifierSymbol(name);
        } else if (data instanceof Double value) {
            return new NumberSymbol(value);
        } else if (data instanceof Character ch) {
            return SpecialSymbol.forChar(ch);
        } else {
            throw new IllegalArgumentException();
        }
    }

}
