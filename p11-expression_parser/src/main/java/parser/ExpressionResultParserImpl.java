package parser;

import expressions.BinaryExpression;
import expressions.Expression;
import expressions.NumberExpression;
import expressions.UnaryExpression;
import scanner.NumberSymbol;
import scanner.Scanner;
import scanner.SpecialSymbol;
import scanner.Symbol;

import java.util.Objects;

public class ExpressionResultParserImpl implements ExpressionResultParser {

    private final Scanner scanner;

    public ExpressionResultParserImpl(Scanner scanner) {
        this.scanner = Objects.requireNonNull(scanner);
        this.scanner.next();
    }

    @Override
    public Expression parse() {
        final Expression value = this.parseAdditiveExpression();
        if (this.scanner.current() != null) {
            throw new ParserException("EOS expected - but found: " + this.scanner.current());
        }
        return value;
    }

    private Expression parseAdditiveExpression() {
        Expression value = this.parseMultiplicativeExpression();
        while (this.scanner.current() == SpecialSymbol.PLUS || this.scanner.current() == SpecialSymbol.MINUS) {
            final boolean plus = this.scanner.current() == SpecialSymbol.PLUS;
            this.scanner.next();
            final Expression v = this.parseMultiplicativeExpression();
            if (plus) {
                value = new BinaryExpression(BinaryExpression.PLUS, value, v);
            } else {
                value = new BinaryExpression(BinaryExpression.MINUS, value, v);
            }
        }
        return value;
    }

    private Expression parseMultiplicativeExpression() {
        Expression value = this.parseAtomicExpression();
        while (this.scanner.current() == SpecialSymbol.TIMES || this.scanner.current() == SpecialSymbol.DIV) {
            final boolean times = this.scanner.current() == SpecialSymbol.TIMES;
            this.scanner.next();
            final Expression v = this.parseAtomicExpression();
            if (times) {
                value = new BinaryExpression(BinaryExpression.TIMES, value, v);
            } else {
                value = new BinaryExpression(BinaryExpression.DIV, value, v);
            }
        }
        return value;
    }

    private Expression parseAtomicExpression() {
        Expression value;
        Symbol unary = null;
        if (this.scanner.current() == SpecialSymbol.PLUS || this.scanner.current() == SpecialSymbol.MINUS) {
            unary = this.scanner.current();
            this.scanner.next();
        }
        if (this.scanner.current() instanceof NumberSymbol) {
            value = new NumberExpression(((NumberSymbol) this.scanner.current()).value());
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
            value = new UnaryExpression(UnaryExpression.PLUS, value);
        } else if (unary == SpecialSymbol.MINUS) {
            value = new UnaryExpression(UnaryExpression.MINUS, value);
        }
        return value;
    }
}
