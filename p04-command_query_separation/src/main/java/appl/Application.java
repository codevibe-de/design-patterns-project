package appl;

import scanner.Scanner;

import java.io.*;


public class Application {

    public static void main(String[] args) throws IOException {
        InputStream resourceStream = Application.class.getResourceAsStream("/zzz.txt");
        try (final Reader reader = new InputStreamReader(resourceStream)) {
            final Scanner scanner = new Scanner(reader);
            printSymbols(scanner);
        }

        System.out.println("---------------------------");

        final Scanner scanner = new Scanner(new StringReader(" 123 + 456 - (789)/*hello World a1 42"));
        printSymbols(scanner);
    }


    private static void printSymbols(Scanner scanner) {
        scanner.next(); // read very first symbol
        while (scanner.current() != null) {
            System.out.println(scanner.current());  // no worries calling current() again!!
            scanner.next();
        }
    }

}
