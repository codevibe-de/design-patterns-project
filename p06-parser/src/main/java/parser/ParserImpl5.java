package parser;

import scanner.NumberSymbol;
import scanner.Scanner;
import scanner.SpecialSymbol;

import java.util.Objects;

public class ParserImpl5 implements Parser {

    private final Scanner scanner;

    public ParserImpl5(Scanner scanner) {
        this.scanner = Objects.requireNonNull(scanner);
        this.scanner.next();
    }

    @Override
    public double parse() {
        double value = this.parseNumber();
        while (this.scanner.current() == SpecialSymbol.TIMES || this.scanner.current() == SpecialSymbol.DIV) {
            final boolean times = this.scanner.current() == SpecialSymbol.TIMES;
            this.scanner.next();
            final double v = this.parseNumber();
            if (times) {
                value = value * v;
            } else {
                value = value / v;
            }
        }
        if (this.scanner.current() != null) {
            throw new ParserException("EOS expected - but found: " + this.scanner.current());
        }
        return value;
    }

    private double parseNumber() {
        if (!(this.scanner.current() instanceof NumberSymbol)) {
            throw new ParserException("number expected - but found: " + this.scanner.current());
        }
        final double value = ((NumberSymbol) this.scanner.current()).value();
        this.scanner.next();
        return value;
    }
}
