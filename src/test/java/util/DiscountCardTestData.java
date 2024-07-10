package util;

import ru.clevertec.check.model.DiscountCard;

import java.math.BigDecimal;

public class DiscountCardTestData {

    public static DiscountCard getDiscountCard() {
        return DiscountCard.builder()
                .id(1L)
                .number(1111)
                .balance(BigDecimal.valueOf(100))
                .discountAmount((byte)3)
                .build();
    }
}
