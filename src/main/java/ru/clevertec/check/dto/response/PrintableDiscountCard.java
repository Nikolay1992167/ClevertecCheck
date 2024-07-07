package main.java.ru.clevertec.check.dto.response;

import main.java.ru.clevertec.check.dto.BalancedDiscountCard;

import java.io.IOException;
import java.io.Writer;

import static main.java.ru.clevertec.check.util.Constants.DELIMITER;
import static main.java.ru.clevertec.check.util.Constants.PERCENTAGE;

public class PrintableDiscountCard extends AbstractTitlePrintable {

    private final BalancedDiscountCard balancedDiscountCard;

    public PrintableDiscountCard(BalancedDiscountCard balancedDiscountCard) {
        this.balancedDiscountCard = balancedDiscountCard;
    }

    @Override
    protected String getTitle() {
        return "DISCOUNT CARD" + DELIMITER + "DISCOUNT PERCENTAGE\n";
    }

    @Override
    protected void printBody(Writer writer) throws IOException {
        writer.append(balancedDiscountCard.number().toString())
                .append(DELIMITER)
                .append(balancedDiscountCard.discountPercentage().toString())
                .append(PERCENTAGE)
                .append('\n');
    }
}