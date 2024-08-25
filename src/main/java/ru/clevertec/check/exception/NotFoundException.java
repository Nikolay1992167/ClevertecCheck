package ru.clevertec.check.exception;

public class NotFoundException extends AbstractPrintableException {

    public NotFoundException() {
        super(BAD_REQUEST);
    }
}