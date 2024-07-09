package main.java.ru.clevertec.check.validation.impl;

import main.java.ru.clevertec.check.exception.ValidationException;
import main.java.ru.clevertec.check.validation.Validator;

import java.util.Arrays;
import java.util.Objects;

public class ValidatorImpl implements Validator<String[]> {

    /**
     * Проверяет аргументы для приложения
     * Должны присутствовать:
     * <pre>
     *     - Хотя бы 1 ID товара (целое число)
     *     - Баланс карточки (целое число, число с плавающей точкой)
     * </pre>
     * Опционально:
     * <pre>
     *     - Номер карты [отсутствовать, null, целое четырёхзначное число]
     * </pre>
     *
     * @param args Аргументы приложения
     */
    @Override
    public void validate(String[] args) {
        checkExistAnyProducts(args);
        checkExistBalance(args);
        checkArgs(args);
    }

    @Override
    public void validatePathArgs(String[] args) {
        boolean pathToFileExists = Arrays.stream(args)
                .anyMatch(arg -> arg.contains("pathToFile"));
        boolean saveToFileExists = Arrays.stream(args)
                .anyMatch(arg -> arg.contains("saveToFile"));

        if (!pathToFileExists || !saveToFileExists) {
            throw new ValidationException();
        }
    }

    private void checkExistAnyProducts(String[] args) {
        if (Arrays.stream(args)
                .noneMatch(arg -> arg.matches("(\\d+--?\\d+)"))) {
            throw new ValidationException();
        }
    }

    private void checkExistBalance(String[] args) {
        if (Arrays.stream(args)
                .filter(arg -> arg.matches("(balanceDebitCard=-?(\\d+\\.?\\d*))"))
                .count() != 1) {
            throw new ValidationException();
        }
    }

    private void checkArgs(String[] args) {
        boolean isAllArgsMatch = Arrays.stream(args)
                .filter(Objects::nonNull)
                .filter(arg -> !arg.trim().isEmpty())
                .filter(arg -> arg.matches("(\\d+--?\\d+)|(discountCard=((\\d{4})|(null)))|(balanceDebitCard=-?(\\d+\\.?\\d*))"))
                .count() == args.length;

        if (!isAllArgsMatch) {
            throw new ValidationException();
        }
    }
}