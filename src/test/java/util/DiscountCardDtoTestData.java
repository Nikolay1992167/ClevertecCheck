package util;

import ru.clevertec.check.dto.request.DiscountCardDto;

public class DiscountCardDtoTestData {

    public static DiscountCardDto getDiscountCardDto() {
        return DiscountCardDto.builder()
                .number("1111")
                .balance("100")
                .build();
    }

    public static DiscountCardDto getDiscountCardDtoWithOutNumber() {
        return DiscountCardDto.builder()
                .number(null)
                .balance("100")
                .build();
    }
}
