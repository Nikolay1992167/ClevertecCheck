package util;

import ru.clevertec.check.dto.request.Bucket;

public class BucketTestData {

    public static Bucket getBucket() {
        return Bucket.builder()
                .products(ProductsDtoTestData.getListProductDto())
                .discountCard(DiscountCardDtoTestData.getDiscountCardDto())
                .build();
    }
}