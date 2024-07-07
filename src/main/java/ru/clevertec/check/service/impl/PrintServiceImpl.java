package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.dto.response.Printable;
import main.java.ru.clevertec.check.exception.FileCreationException;
import main.java.ru.clevertec.check.exception.PrintableException;
import main.java.ru.clevertec.check.service.PrintService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;

public class PrintServiceImpl implements PrintService {

    /**
     * Метод создаёт {@link Writer} и делегирует {@link Printable} печать
     *
     * @param filepath  Путь к файлу для печати
     * @param printable Объект класса {@link Printable}, в котором реализована логика печати в {@link Writer}
     */
    @Override
    public void printToFile(Path filepath, Printable printable) {
        File file = creteFile(filepath);

        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            printable.print(writer);

        } catch (IOException exception) {
            throw new PrintableException();
        }
    }

    /**
     * Метод создаёт {@link Writer} для печати в консоль и делегирует объекту {@link Printable} печать
     *
     * @param printable Объект класса {@link Printable}, в котором реализована логика печати в {@link Writer}
     */
    @Override
    public void printToConsole(Printable printable) {
        try {
            Writer writer = new OutputStreamWriter(System.out);
            printable.print(writer);
            writer.flush();

        } catch (IOException exception) {
            throw new PrintableException();
        }
    }

    private File creteFile(Path path) {
        File file = path.toFile();

        try {
            if (!file.exists() && !file.createNewFile()) {
                throw new FileCreationException();
            }

        } catch (IOException e) {
            throw new FileCreationException();

        }

        return file;
    }
}