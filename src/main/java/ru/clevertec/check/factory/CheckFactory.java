package main.java.ru.clevertec.check.factory;

import main.java.ru.clevertec.check.dto.BalancedDiscountCard;
import main.java.ru.clevertec.check.dto.ProductInfo;
import main.java.ru.clevertec.check.dto.response.Check;

import java.util.List;

public interface CheckFactory {

    Check createCheck(List<ProductInfo> productInfoList, BalancedDiscountCard discountCard);
}