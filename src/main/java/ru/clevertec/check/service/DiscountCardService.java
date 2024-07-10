package ru.clevertec.check.service;

import ru.clevertec.check.dto.BalancedDiscountCard;
import ru.clevertec.check.dto.request.DiscountCardDto;

public interface DiscountCardService {

    BalancedDiscountCard getWithBalance(DiscountCardDto discountCardDto);
}