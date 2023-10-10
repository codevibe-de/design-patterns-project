package appl;

import parser.Parser;
import parser.ParserImpl;
import scanner.Scanner;
import scanner.ScannerImpl;

import java.io.IOException;
import java.io.StringReader;

public class Application {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new ScannerImpl(new StringReader("  (+6 + 3.0) * -4 "));
        Parser parser = new ParserImpl(scanner);
        System.out.println(parser.parse());

        scanner = new ScannerImpl(new StringReader("0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1"));
        parser = new ParserImpl(scanner);
        System.out.println(parser.parse());
    }

}
