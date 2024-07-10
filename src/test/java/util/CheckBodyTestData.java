package util;

import ru.clevertec.check.dto.response.CheckBody;

public class CheckBodyTestData {

    public static CheckBody getCheckBody() {
        return new CheckBody(OrderResponseDtoTestData.getListOrderResponseDto());
    }
}
