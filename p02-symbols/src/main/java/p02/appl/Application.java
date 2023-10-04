package p02.appl;

import p02.scanner.Scanner;
import p02.scanner.Symbol;

import java.io.*;


public class Application {
    public static void main(String[] args) throws IOException {

        try (final Reader reader = new InputStreamReader(new FileInputStream("src/zzz.txt"))) {
            final Scanner scanner = new Scanner(reader);
            for (Symbol symbol = scanner.readSymbol(); symbol != null; symbol = scanner.readSymbol())
                System.out.println(symbol);
        }

        System.out.println("---------------------------");

        final Scanner scanner = new Scanner(new StringReader(" 123 + 456 - (789)/*hello World a1 42"));
        for (Symbol symbol = scanner.readSymbol(); symbol != null; symbol = scanner.readSymbol())
            System.out.println(symbol);

    }
}
