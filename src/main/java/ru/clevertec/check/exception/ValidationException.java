package main.java.ru.clevertec.check.exception;

public class ValidationException extends AbstractPrintableException {

    public ValidationException() {
        super(BAD_REQUEST);
    }
}