package ru.clevertec.check.dto.response;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static ru.clevertec.check.util.Constants.CURRENCY;
import static ru.clevertec.check.util.Constants.DELIMITER;

public class OrderResponseDto implements Printable {

    private final String description;
    private final BigDecimal price;
    private final Integer count;
    private final BigDecimal total;
    private final BigDecimal totalDiscount;

    public OrderResponseDto(String description,
                            BigDecimal price,
                            Integer count,
                            Byte discount) {
        this.description = description;
        this.price = price;
        this.count = count;
        total = price.multiply(BigDecimal.valueOf(count))
                .setScale(2, HALF_UP);
        totalDiscount = getTotal().multiply(BigDecimal.valueOf(discount))
                .multiply(BigDecimal.valueOf(0.01))
                .setScale(2, HALF_UP);
    }

    @Override
    public void print(Writer writer) throws IOException {
        writer.append(count.toString())
                .append(DELIMITER)
                .append(description)
                .append(DELIMITER)
                .append(price.toString())
                .append(CURRENCY)
                .append(DELIMITER)
                .append(getDiscount().toString())
                .append(CURRENCY)
                .append(DELIMITER)
                .append(price.multiply(BigDecimal.valueOf(count)).setScale(2, HALF_UP).toString())
                .append(CURRENCY)
                .append('\n');
    }

    public BigDecimal getDiscount() {
        return totalDiscount;
    }

    public BigDecimal getTotal() {
        return total;
    }
}