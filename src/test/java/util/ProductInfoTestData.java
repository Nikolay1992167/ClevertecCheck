package util;

import ru.clevertec.check.dto.ProductInfo;

import java.math.BigDecimal;
import java.util.List;

public class ProductInfoTestData {

    public static List<ProductInfo> getListProductInfo() {
        return List.of(
                ProductInfo.builder()
                        .description("Milk 1l")
                        .count(100)
                        .price(BigDecimal.valueOf(2.08))
                        .tradePrice(false)
                        .build(),
                ProductInfo.builder()
                        .description("Still water 1l")
                        .count(20)
                        .price(BigDecimal.valueOf(1.5))
                        .tradePrice(false)
                        .build()
        );
    }
}
