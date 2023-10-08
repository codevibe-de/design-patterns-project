package parser;

import scanner.NumberSymbol;
import scanner.Scanner;
import scanner.SpecialSymbol;

import java.util.Objects;

public class ParserImpl7 implements Parser {

    private final Scanner scanner;

    public ParserImpl7(Scanner scanner) {
        this.scanner = Objects.requireNonNull(scanner);
        this.scanner.next();
    }

    @Override
    public double parse() {
        final double value = this.parseAdditiveExpression();
		if (this.scanner.current() != null) {
			throw new ParserException("EOS expected - but found: " + this.scanner.current());
		}
        return value;
    }

    private double parseAdditiveExpression() {
        double value = this.parseMultiplicativeExpression();
        while (this.scanner.current() == SpecialSymbol.PLUS || this.scanner.current() == SpecialSymbol.MINUS) {
            final boolean plus = this.scanner.current() == SpecialSymbol.PLUS;
            this.scanner.next();
            final double v = this.parseMultiplicativeExpression();
			if (plus) {
				value = value + v;
			} else {
				value = value - v;
			}
        }
        return value;
    }

    private double parseMultiplicativeExpression() {
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
