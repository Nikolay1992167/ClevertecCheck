package util;

import ru.clevertec.check.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductTestData {

    public static List<Product> getListProducts() {
        return List.of(
                Product.builder()
                        .id(1L)
                        .description("Milk 1l")
                        .price(BigDecimal.valueOf(2.05))
                        .quantityInStock(2)
                        .wholesaleProduct(false)
                        .build(),
                Product.builder()
                        .id(2l)
                        .description("Still water 1l")
                        .price((BigDecimal.valueOf(1.50)))
                        .quantityInStock(2)
                        .wholesaleProduct(false)
                        .build());
    }

    public static List<Product> getListProductsAll() {
        return List.of(
                Product.builder()
                        .id(1L)
                        .description("Milk 1l")
                        .price(BigDecimal.valueOf(2.05))
                        .quantityInStock(10)
                        .wholesaleProduct(false)
                        .build(),
                Product.builder()
                        .id(2l)
                        .description("Still water 1l")
                        .price((BigDecimal.valueOf(1.50)))
                        .quantityInStock(10)
                        .wholesaleProduct(false)
                        .build());
    }
}
