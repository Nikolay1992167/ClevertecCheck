package main.java.ru.clevertec.check.config;

import main.java.ru.clevertec.check.dto.ArgsWrapper;
import main.java.ru.clevertec.check.dto.CommandLineArgumentContainer;
import main.java.ru.clevertec.check.dto.response.Printable;
import main.java.ru.clevertec.check.exception.AbstractPrintableException;
import main.java.ru.clevertec.check.exception.FileCreationException;
import main.java.ru.clevertec.check.service.PrintService;
import main.java.ru.clevertec.check.service.impl.PrintServiceImpl;
import main.java.ru.clevertec.check.validation.Validator;
import main.java.ru.clevertec.check.validation.impl.ValidatorImpl;

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
                    if (arg.startsWith("pathToFile")) {
                        argsWrapper.putProperty(ArgsWrapper.READ_FROM_FILE_PATH, arg.split("=")[1]);

                    } else if (arg.startsWith("saveToFile")) {
                        argsWrapper.putProperty(ArgsWrapper.SAVE_TO_FILE_PATH, arg.split("=")[1]);

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