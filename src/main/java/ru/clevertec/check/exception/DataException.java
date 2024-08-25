package ru.clevertec.check.exception;

public class DataException extends AbstractPrintableException {

    public DataException() {
        super(BAD_REQUEST);
    }
}