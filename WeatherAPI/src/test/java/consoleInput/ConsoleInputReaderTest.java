package consoleInput;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class ConsoleInputReaderTest {
    private ConsoleInputReader consoleInputReader;
    private Scanner scanner;
    private InputStream inputStream;
    private String userInput;

    @Before
    public void initObjects() {
        consoleInputReader = new ConsoleInputReader();
        userInput = "test";
        inputStream = System.in;
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        scanner = new Scanner(System.in);
        System.setIn(inputStream);
    }

    @Test
    public void testIfInputReaderReturnsCorrectUserInput() {
        assertEquals(userInput, consoleInputReader.readInputFromConsole("Insert something: ", scanner));
    }

}
