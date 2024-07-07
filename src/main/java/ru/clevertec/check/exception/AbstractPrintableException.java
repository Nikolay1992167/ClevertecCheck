package main.java.ru.clevertec.check.exception;

import main.java.ru.clevertec.check.dto.response.Printable;

import java.io.IOException;
import java.io.Writer;

public abstract class AbstractPrintableException extends RuntimeException implements Printable {

    public static final String ERROR = "ERROR";
    public static final String BAD_REQUEST = "BAD REQUEST";
    public static final String BALANCE_NOT_AVAILABLE = "NOT ENOUGH MONEY";
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR";


    public AbstractPrintableException(String message) {
        super(message);
    }

    @Override
    public void print(Writer writer) {
        try {
            writer.append(ERROR)
                    .append('\n')
                    .append(this.getMessage())
                    .append('\n')
                    .flush();

        } catch (IOException e) {
            throw new PrintableException();
        }
    }
}
