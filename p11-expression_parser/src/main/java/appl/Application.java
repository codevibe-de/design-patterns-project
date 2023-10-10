package appl;

import parser.ExpressionResultParser;
import parser.ExpressionResultParserImpl;
import scanner.Scanner;
import scanner.ScannerImpl;

import java.io.IOException;
import java.io.StringReader;

public class Application {

    public static void main(String[] args) throws IOException {
        final Scanner scanner = new ScannerImpl(new StringReader("  (+6 + 3.0) * -4 "));
        final ExpressionResultParser parser = new ExpressionResultParserImpl(scanner);
        System.out.println(parser.parse().evaluate());
    }

}
