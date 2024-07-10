package util;

import ru.clevertec.check.dto.BalancedDiscountCard;

import java.math.BigDecimal;

public class BalancedDiscountCardTestData {

    public static BalancedDiscountCard getBalancedDiscountCard() {
        return BalancedDiscountCard.builder()
                .number(1111)
                .balance(BigDecimal.valueOf(100))
                .discountPercentage((byte)4)
                .build();
    }
}
