package ru.clevertec.check.dto;

import java.math.BigDecimal;

public record BalancedDiscountCard(BigDecimal balance, Integer number, Byte discountPercentage) {

    public boolean isDefault() {
        return number == null;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BigDecimal balance;
        private Integer number;
        private Byte discountPercentage;

        public Builder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder number(Integer number) {
            this.number = number;
            return this;
        }

        public Builder discountPercentage(Byte discountPercentage) {
            this.discountPercentage = discountPercentage;
            return this;
        }

        public BalancedDiscountCard build() {
            return new BalancedDiscountCard(balance, number, discountPercentage);
        }
    }
}
