package appl;

import scanner.Scanner;
import scanner.ScannerImpl;

import java.io.*;


public class Application {

    public static void main(String[] args) throws IOException {
        InputStream resourceStream = Application.class.getResourceAsStream("/zzz.txt");
        try (final Reader reader = new InputStreamReader(resourceStream)) {
            final Scanner scanner = new ScannerImpl(reader);
            for (scanner.next(); scanner.current() != null; scanner.next())
                System.out.println(scanner.current());
        }

        System.out.println("---------------------------");

        final Scanner scanner = new ScannerImpl(new StringReader(" 123 + 456 - (789)/*hello World a1 42"));
        for (scanner.next(); scanner.current() != null; scanner.next())
            System.out.println(scanner.current());
    }

}
