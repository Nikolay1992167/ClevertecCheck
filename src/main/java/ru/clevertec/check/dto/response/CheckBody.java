package main.java.ru.clevertec.check.dto.response;

import main.java.ru.clevertec.check.exception.PrintableException;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;

import static main.java.ru.clevertec.check.util.Constants.DELIMITER;

public class CheckBody extends AbstractTitlePrintable {

    private final List<OrderResponseDto> goodList;
    private final BigDecimal totalPrice;
    private final BigDecimal totalDiscount;

    public CheckBody(List<OrderResponseDto> orderResponseList) {
        this.goodList = orderResponseList;
        this.totalDiscount = orderResponseList.stream()
                .map(OrderResponseDto::getDiscount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        this.totalPrice = orderResponseList.stream()
                .map(OrderResponseDto::getTotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    @Override
    protected String getTitle() {
        return "QTY" +
                DELIMITER +
                "DESCRIPTION" +
                DELIMITER +
                "PRICE" +
                DELIMITER +
                "DISCOUNT" +
                DELIMITER +
                "TOTAL\n";
    }

    @Override
    protected void printBody(Writer writer) {
        goodList.forEach(good -> print(good, writer));
    }

    private void print(Printable printable, Writer writer) {
        try {
            printable.print(writer);
        } catch (IOException e) {
            throw new PrintableException();
        }
    }
}