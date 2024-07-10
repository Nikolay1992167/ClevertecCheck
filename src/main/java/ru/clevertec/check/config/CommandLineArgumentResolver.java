package ru.clevertec.check.config;

import ru.clevertec.check.dto.ArgsWrapper;
import ru.clevertec.check.dto.CommandLineArgumentContainer;
import ru.clevertec.check.dto.response.Printable;
import ru.clevertec.check.exception.AbstractPrintableException;
import ru.clevertec.check.exception.FileCreationException;
import ru.clevertec.check.service.PrintService;
import ru.clevertec.check.service.impl.PrintServiceImpl;
import ru.clevertec.check.validation.Validator;
import ru.clevertec.check.validation.impl.ValidatorImpl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class CommandLineArgumentResolver {

    private static final Validator<String[]> validator = new ValidatorImpl();
    private static final PrintService printService = new PrintServiceImpl();

    public static CommandLineArgumentContainer splitArgs(String[] args) {

        try {
            validator.validatePathArgs(args);
        } catch (AbstractPrintableException printable) {
            print(printable);
            System.exit(1);
        }

        ArgsWrapper argsWrapper = new ArgsWrapper();
        Arrays.stream(args)
                .forEach(arg ->
                {
                    if (arg.startsWith("saveToFile")) {
                        argsWrapper.putProperty(ArgsWrapper.SAVE_TO_FILE_PATH, arg.split("=")[1]);
                    } else if (arg.startsWith("datasource.url")) {
                        argsWrapper.putProperty("url", arg.split("=")[1]);
                    } else if (arg.startsWith("datasource.username")) {
                        argsWrapper.putProperty("username", arg.split("=")[1]);
                    } else if (arg.startsWith("datasource.password")) {
                        argsWrapper.putProperty("password", arg.split("=")[1]);
                    } else {
                        argsWrapper.addAppArg(arg);
                    }
                });
        return argsWrapper;
    }

    private static void print(Printable printable) {
        Path path = Paths.get("./result.csv");
        try {
            printService.printToFile(path, printable);
            printService.printToConsole(printable);

        } catch (FileCreationException fileCreationException) {
            printService.printToFile(path, fileCreationException);
            printService.printToConsole(printable);
        }
    }
}