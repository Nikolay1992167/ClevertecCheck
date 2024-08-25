package util;

import ru.clevertec.check.dto.request.ProductDto;

import java.util.List;

public class ProductsDtoTestData {

    public static List<ProductDto> getListProductDto() {
        return List.of(
                ProductDto.builder()
                        .id(1L)
                        .quantity(4)
                        .build(),
                ProductDto.builder()
                        .id(2L)
                        .quantity(5)
                        .build()
        );
    }
}
