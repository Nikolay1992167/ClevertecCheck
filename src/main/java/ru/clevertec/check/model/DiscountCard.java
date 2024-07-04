package main.java.ru.clevertec.check.model;

import java.math.BigDecimal;
import java.util.Objects;

public class DiscountCard {

    private Long id;
    private Integer number;
    private Integer discountAmount;
    private BigDecimal balance;

    public DiscountCard() {
    }

    public DiscountCard(Long id, Integer number, Integer discountAmount, BigDecimal balance) {
        this.id = id;
        this.number = number;
        this.discountAmount = discountAmount;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "id=" + id +
                ", number=" + number +
                ", discountAmount=" + discountAmount +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DiscountCard that = (DiscountCard) object;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(discountAmount, that.discountAmount) && Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, discountAmount, balance);
    }

    public static class Builder {

        private Long id;
        private Integer number;
        private Integer discountAmount;
        private BigDecimal balance;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder number(Integer number) {
            this.number = number;
            return this;
        }

        public Builder discountAmount(Integer discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }

        public Builder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public DiscountCard build() {
            return new DiscountCard(id, number, discountAmount, balance);
        }
    }
}
