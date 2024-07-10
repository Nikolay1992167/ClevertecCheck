package util;

import ru.clevertec.check.dto.response.OrderResponseDto;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponseDtoTestData {

    public static List<OrderResponseDto> getListOrderResponseDto() {
        return List.of(
                new OrderResponseDto("Lemonade 1l", BigDecimal.valueOf(1.01), 3, (byte)4),
                new OrderResponseDto("Orange juice 1l", BigDecimal.valueOf(3.50), 1, (byte)2)
        );
    }
}
