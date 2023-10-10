package parser;

import scanner.NumberSymbol;
import scanner.Scanner;

import java.util.Objects;

public class ParserImpl2 implements Parser {

    private final Scanner scanner;

    public ParserImpl2(Scanner scanner) {
        this.scanner = Objects.requireNonNull(scanner);
        this.scanner.next();
    }

    @Override
    public double parse() {
        if (!(this.scanner.current() instanceof NumberSymbol)) {
            throw new ParserException("number expected - but found: " + this.scanner.current());
        }
        final double value = ((NumberSymbol) this.scanner.current()).value();
        this.scanner.next();
        if (this.scanner.current() != null) {
            throw new ParserException("EOS expected - but found: " + this.scanner.current());
        }
        return value;
    }
}
