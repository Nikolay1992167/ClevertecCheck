package main.java.ru.clevertec.check.dto.response;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;

import static main.java.ru.clevertec.check.util.Constants.CURRENCY;
import static main.java.ru.clevertec.check.util.Constants.DELIMITER;

public class  CheckTotal extends AbstractTitlePrintable {

    private final CheckBody checkBody;

    public CheckTotal(CheckBody checkBody) {
        this.checkBody = checkBody;
    }

    @Override
    protected String getTitle() {
        return "TOTAL PRICE" + DELIMITER + "TOTAL DISCOUNT" + DELIMITER + "TOTAL WITH DISCOUNT\n";
    }

    @Override
    protected void printBody(Writer writer) throws IOException {
        BigDecimal totalPrice = checkBody.getTotalPrice();
        BigDecimal totalDiscount = checkBody.getTotalDiscount();

        writer.append(totalPrice.toString())
                .append(CURRENCY)
                .append(DELIMITER)
                .append(totalDiscount.toString())
                .append(CURRENCY)
                .append(DELIMITER)
                .append(totalPrice.subtract(totalDiscount).toString())
                .append(CURRENCY);
    }
}
