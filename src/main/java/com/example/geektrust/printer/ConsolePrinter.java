package com.example.geektrust.printer;

public class ConsolePrinter implements Printer {

    @Override
    public void print(String line) {
        System.out.println(line);
    }
}
