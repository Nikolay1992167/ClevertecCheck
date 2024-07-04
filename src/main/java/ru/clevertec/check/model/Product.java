package main.java.ru.clevertec.check.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private Long id;
    private String description;
    private BigDecimal price;
    private Integer quantityInStock;
    private Boolean wholesaleProduct;

    public Product() {
    }

    public Product(Long id,
                   String description,
                   BigDecimal price,
                   Integer quantityInStock,
                   Boolean wholesaleProduct) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.wholesaleProduct = wholesaleProduct;
    }

//    public Product(Product product) {
//        this.id = product.id;
//        this.price = product.price;
//        this.description = product.description;
//        this.quantityInStock = product.quantityInStock;
//        this.wholesaleProduct = product.wholesaleProduct;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Boolean getWholesaleProduct() {
        return wholesaleProduct;
    }

    public void setWholesaleProduct(Boolean wholesaleProduct) {
        this.wholesaleProduct = wholesaleProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(quantityInStock, product.quantityInStock) && Objects.equals(wholesaleProduct, product.wholesaleProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, quantityInStock, wholesaleProduct);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantityInStock=" + quantityInStock +
                ", wholesaleProduct=" + wholesaleProduct +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String description;
        private BigDecimal price;
        private Integer quantityInStock;
        private Boolean wholesaleProduct;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder quantityInStock(Integer quantityInStock) {
            this.quantityInStock = quantityInStock;
            return this;
        }

        public Builder wholesaleProduct(Boolean wholesaleProduct) {
            this.wholesaleProduct = wholesaleProduct;
            return this;
        }

        public Product build() {
            return new Product(id, description, price, quantityInStock, wholesaleProduct);
        }
    }
}
