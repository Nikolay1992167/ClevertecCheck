package main.java.ru.clevertec.check.mapper;

import main.java.ru.clevertec.check.dto.BalancedDiscountCard;
import main.java.ru.clevertec.check.dto.request.DiscountCardDto;
import main.java.ru.clevertec.check.model.DiscountCard;

import java.math.BigDecimal;

public interface DiscountCardMapper {

    BalancedDiscountCard toBalancedDiscountCard(DiscountCard discountCard, BigDecimal balance);

    BalancedDiscountCard toDefaultDiscountCard(DiscountCardDto discountCardDto);

    BalancedDiscountCard toNoneDiscountCard(DiscountCardDto discountCardDto);
}
