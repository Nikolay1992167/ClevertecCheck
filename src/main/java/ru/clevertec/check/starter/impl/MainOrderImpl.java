package main.java.ru.clevertec.check.starter.impl;

import main.java.ru.clevertec.check.controller.MainOrderController;
import main.java.ru.clevertec.check.dto.request.Bucket;
import main.java.ru.clevertec.check.dto.response.Check;
import main.java.ru.clevertec.check.dto.response.Printable;
import main.java.ru.clevertec.check.exception.AbstractPrintableException;
import main.java.ru.clevertec.check.exception.FileCreationException;
import main.java.ru.clevertec.check.mapper.ArgMapper;
import main.java.ru.clevertec.check.service.PrintService;
import main.java.ru.clevertec.check.starter.MainOrder;
import main.java.ru.clevertec.check.validation.Validator;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MainOrderImpl implements MainOrder {

    private final ArgMapper argMapper;
    private final PrintService printService;
    private final MainOrderController orderController;
    private final Validator<String[]> validator;

    public MainOrderImpl(ArgMapper argMapper,
                                  PrintService printService,
                                  MainOrderController orderController,
                                  Validator<String[]> validator) {
        this.argMapper = argMapper;
        this.printService = printService;
        this.orderController = orderController;
        this.validator = validator;
    }

    /**
     * Path to the saved file
     */
    private final String filePath = "./result.csv";

    /**
     * Запускает процесс обработки заказа из аргументов
     *
     * @param args Аргументы заказа
     */
    @Override
    public void processOrder(String[] args) {
        try {
            validator.validate(args);
            Bucket bucket = argMapper.parseArg(args);
            Check check = orderController.createCheck(bucket);
            print(check);

        } catch (AbstractPrintableException printable) {
            print(printable);
        }
    }

    private void print(Printable printable) {
        Path path = Paths.get(filePath);
        try {
            printService.printToFile(path, printable);
            printService.printToConsole(printable);

        } catch (FileCreationException fileCreationException) {
            printService.printToFile(path, fileCreationException);
            printService.printToConsole(printable);
        }
    }
}