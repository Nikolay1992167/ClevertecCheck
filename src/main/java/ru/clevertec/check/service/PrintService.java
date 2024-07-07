package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.dto.response.Printable;

import java.nio.file.Path;

public interface PrintService {

    void printToFile(Path filepath, Printable printable);

    void printToConsole(Printable printable);
}