package ru.clevertec.check.starter.impl;

import ru.clevertec.check.controller.MainOrderController;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.dto.response.Check;
import ru.clevertec.check.dto.response.Printable;
import ru.clevertec.check.exception.FileCreationException;
import ru.clevertec.check.mapper.ArgMapper;
import ru.clevertec.check.service.PrintService;
import ru.clevertec.check.exception.AbstractPrintableException;
import ru.clevertec.check.starter.MainOrder;
import ru.clevertec.check.validation.Validator;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MainOrderImpl implements MainOrder {

    /**
     * Path to the saved file
     */
    private final String filePath;
    private final ArgMapper argMapper;
    private final PrintService printService;
    private final MainOrderController orderController;
    private final Validator<String[]> validator;

    public MainOrderImpl(ArgMapper argMapper,
                                  PrintService printService,
                                  MainOrderController orderController,
                                  Validator<String[]> validator,
                                  String filePath) {
        this.argMapper = argMapper;
        this.printService = printService;
        this.orderController = orderController;
        this.validator = validator;
        this.filePath = filePath;
    }


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