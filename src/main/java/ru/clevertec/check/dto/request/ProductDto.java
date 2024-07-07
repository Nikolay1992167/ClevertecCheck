package main.java.ru.clevertec.check.dto.request;

public record ProductDto(Long id, Integer quantity) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Integer quantity;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductDto build() {
            return new ProductDto(id, quantity);
        }
    }
}