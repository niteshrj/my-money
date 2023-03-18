package com.example.geektrust;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {

    @Test
    void shouldCreateAndExecuteCommandsFromGivenFile() throws FileNotFoundException {
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Main.main(new String[]{"src/test/resources/input.txt"});

        String expectedPrintOutput = "10593 7897 2272\n23619 11809 3936";
        assertEquals(expectedPrintOutput, outputStream.toString().trim());

        System.setOut(standardOut);
    }

    @Test
    void shouldThrowFileNotFoundException() {
        assertThrows(FileNotFoundException.class, () -> Main.main(new String[]{"src/test/resources/dummyFile.txt"}));
    }
}