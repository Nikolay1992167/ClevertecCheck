package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.dto.BalancedDiscountCard;
import main.java.ru.clevertec.check.dto.request.DiscountCardDto;

public interface DiscountCardService {

    BalancedDiscountCard getWithBalance(DiscountCardDto discountCardDto);
}