package com.example.geektrust;

import com.example.geektrust.command.CommandInvoker;
import com.example.geektrust.model.Portfolio;
import com.example.geektrust.printer.ConsolePrinter;
import com.example.geektrust.printer.Printer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            Path filePath = Paths.get(args[0]);
            List<String> lines = Files.readAllLines(filePath);
            Printer consolePrinter = new ConsolePrinter();
            Portfolio portfolio = new Portfolio();
            CommandInvoker invoker = new CommandInvoker(portfolio, consolePrinter);
            lines.forEach(invoker::addCommand);
            invoker.executeCommands();
        } catch (IOException e) {
            throw new FileNotFoundException("FILE_NOT_FOUND");
        }
    }
}
