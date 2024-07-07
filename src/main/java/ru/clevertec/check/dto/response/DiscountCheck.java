package main.java.ru.clevertec.check.dto.response;

import main.java.ru.clevertec.check.exception.PrintableException;

import java.io.IOException;
import java.io.Writer;

public class DiscountCheck extends Check {

    private final PrintableDiscountCard printableDiscountCard;

    public DiscountCheck(CheckTitle checkTitle,
                         CheckBody checkBody,
                         CheckTotal checkTotal,
                         PrintableDiscountCard printableDiscountCard) {
        super(checkTitle, checkBody, checkTotal);
        this.printableDiscountCard = printableDiscountCard;
    }

    @Override
    public void print(Writer writer) {
        try {
            getCheckTitle().print(writer);
            writer.write('\n');
            getCheckBody().print(writer);
            writer.write('\n');
            printableDiscountCard.print(writer);
            writer.write('\n');
            getCheckTotal().print(writer);
            writer.write('\n');

        } catch (IOException e) {
            throw new PrintableException();
        }
    }

    public static Builder discountBuilder() {
        return new Builder();
    }

    public static class Builder {

        private CheckTitle checkTitle;
        private CheckBody checkBody;
        private CheckTotal checkTotal;
        private PrintableDiscountCard printableDiscountCard;

        public Builder checkTitle(CheckTitle checkTitle) {
            this.checkTitle = checkTitle;
            return this;
        }

        public Builder checkBody(CheckBody checkBody) {
            this.checkBody = checkBody;
            return this;
        }

        public Builder checkTotal(CheckTotal checkTotal) {
            this.checkTotal = checkTotal;
            return this;
        }

        public Builder printableDiscountCard(PrintableDiscountCard printableDiscountCard) {
            this.printableDiscountCard = printableDiscountCard;
            return this;
        }

        public DiscountCheck build() {
            return new DiscountCheck(checkTitle, checkBody, checkTotal, printableDiscountCard);
        }
    }
}
