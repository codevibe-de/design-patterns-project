package scanner;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {

    public static final FlyweightFactory INSTANCE = new FlyweightFactory();

    private FlyweightFactory() {
    }

    private final Map<Object, Symbol> symbols = new HashMap<>();

    Symbol getSymbol(final Object data) {
        final Object finalData = (data instanceof Number number) ? number.doubleValue() : data;
        return this.symbols.computeIfAbsent(finalData, v -> Symbol.of(finalData));
    }

}
