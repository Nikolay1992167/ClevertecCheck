package ru.clevertec.check.mapper.impl;

import ru.clevertec.check.dto.ProductInfo;
import ru.clevertec.check.mapper.ProductMapper;
import ru.clevertec.check.model.Product;

import java.math.RoundingMode;

public class ProductMapperImpl implements ProductMapper {

    public ProductInfo toGoodInfo(Product product, Integer count) {
        if (product == null && count == null) {
            return null;

        } else {

            ProductInfo.Builder productInfo = ProductInfo.builder();
            if (product != null) {
                productInfo
                        .tradePrice(product.getWholesaleProduct())
                        .description(product.getDescription())
                        .price(product.getPrice().setScale(2, RoundingMode.HALF_UP));
            }

            return productInfo.count(count).build();
        }
    }
}