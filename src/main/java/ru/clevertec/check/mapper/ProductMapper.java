package main.java.ru.clevertec.check.mapper;

import main.java.ru.clevertec.check.dto.ProductInfo;
import main.java.ru.clevertec.check.model.Product;

public interface ProductMapper {

    ProductInfo toGoodInfo(Product product, Integer count);
}
