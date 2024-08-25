package ru.clevertec.check.mapper;

import ru.clevertec.check.dto.BalancedDiscountCard;
import ru.clevertec.check.dto.request.DiscountCardDto;
import ru.clevertec.check.model.DiscountCard;

import java.math.BigDecimal;

public interface DiscountCardMapper {

    BalancedDiscountCard toBalancedDiscountCard(DiscountCard discountCard, BigDecimal balance);

    BalancedDiscountCard toDefaultDiscountCard(DiscountCardDto discountCardDto);

    BalancedDiscountCard toNoneDiscountCard(DiscountCardDto discountCardDto);
}
