package parser;

import scanner.NumberSymbol;
import scanner.Scanner;
import scanner.SpecialSymbol;
import scanner.Symbol;

import java.util.Objects;

public class ParserImpl implements Parser {

    private final Scanner scanner;

    public ParserImpl(Scanner scanner) {
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
        double value = this.parseAtomicExpression();
        while (this.scanner.current() == SpecialSymbol.TIMES || this.scanner.current() == SpecialSymbol.DIV) {
            final boolean times = this.scanner.current() == SpecialSymbol.TIMES;
            this.scanner.next();
            final double v = this.parseAtomicExpression();
			if (times) {
				value = value * v;
			} else {
				value = value / v;
			}
        }
        return value;
    }

    private double parseAtomicExpression() {
        double value;
        Symbol unary = null;
        if (this.scanner.current() == SpecialSymbol.PLUS || this.scanner.current() == SpecialSymbol.MINUS) {
            unary = this.scanner.current();
            this.scanner.next();
        }
        if (this.scanner.current() instanceof NumberSymbol) {
            value = ((NumberSymbol) this.scanner.current()).value();
            this.scanner.next();
        } else if (this.scanner.current() == SpecialSymbol.OPEN) {
			this.scanner.next();
			value = this.parseAdditiveExpression();
			if (this.scanner.current() != SpecialSymbol.CLOSE) {
				throw new ParserException(") expected - but found: " + this.scanner.current());
			}
			this.scanner.next();
		} else {
			throw new ParserException("number or ( expected - but found: " + this.scanner.current());
		}
		if (unary == SpecialSymbol.PLUS) {
			value = +value;
		} else if (unary == SpecialSymbol.MINUS) {
			value = -value;
		}
        return value;
    }
}
