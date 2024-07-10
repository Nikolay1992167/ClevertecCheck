package ru.clevertec.check.dto.request;

import java.util.List;

public record Bucket(List<ProductDto> products, DiscountCardDto discountCard) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<ProductDto> products;

        private DiscountCardDto discountCard;

        public Builder products(List<ProductDto> products) {
            this.products = products;
            return this;
        }

        public Builder discountCard(DiscountCardDto discountCard) {
            this.discountCard = discountCard;
            return this;
        }

        public Bucket build() {
            return new Bucket(products, discountCard);
        }
    }
}
