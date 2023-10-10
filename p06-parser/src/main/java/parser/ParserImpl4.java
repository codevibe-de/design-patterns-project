package parser;

import scanner.NumberSymbol;
import scanner.Scanner;
import scanner.SpecialSymbol;

import java.util.Objects;

public class ParserImpl4 implements Parser {

    private final Scanner scanner;

    public ParserImpl4(Scanner scanner) {
        this.scanner = Objects.requireNonNull(scanner);
        this.scanner.next();
    }

    @Override
    public double parse() {
        if (!(this.scanner.current() instanceof NumberSymbol)) {
            throw new ParserException("number expected - but found: " + this.scanner.current());
        }
        double value = ((NumberSymbol) this.scanner.current()).value();
        this.scanner.next();
        while (this.scanner.current() == SpecialSymbol.TIMES || this.scanner.current() == SpecialSymbol.DIV) {
            final boolean times = this.scanner.current() == SpecialSymbol.TIMES;
            this.scanner.next();
            if (!(this.scanner.current() instanceof NumberSymbol)) {
                throw new ParserException("number expected - but found: " + this.scanner.current());
            }
            final double v = ((NumberSymbol) this.scanner.current()).value();
            this.scanner.next();
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
}
