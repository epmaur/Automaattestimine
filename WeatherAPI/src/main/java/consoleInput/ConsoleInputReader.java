package consoleInput;

import java.util.Scanner;

public class ConsoleInputReader {

    public String readInputFromConsole(String message, Scanner scanner) {
        System.out.println(message);
        return scanner.nextLine();
    }

}
