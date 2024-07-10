package ru.clevertec.check.exception;

public class QuantityInStockIsNotAvailableException extends AbstractPrintableException {

    public QuantityInStockIsNotAvailableException() {
        super(BAD_REQUEST);
    }
}