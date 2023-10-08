package parser;

import scanner.NumberSymbol;
import scanner.Scanner;

import java.util.Objects;

public class ParserImpl1 implements Parser {

    private final Scanner scanner;

    public ParserImpl1(Scanner scanner) {
        this.scanner = Objects.requireNonNull(scanner);
        this.scanner.next();
    }

    @Override
    public double parse() {
		if (!(this.scanner.current() instanceof NumberSymbol)) {
			throw new ParserException("number expected - but found: " + this.scanner.current());
		}
        return 0;
    }
}
