package ru.clevertec.check.mapper.impl;

import ru.clevertec.check.dto.BalancedDiscountCard;
import ru.clevertec.check.dto.request.DiscountCardDto;
import ru.clevertec.check.mapper.DiscountCardMapper;
import ru.clevertec.check.model.DiscountCard;

import java.math.BigDecimal;

public class DiscountCardMapperImpl implements DiscountCardMapper {

    public BalancedDiscountCard toBalancedDiscountCard(DiscountCard discountCard, BigDecimal balance) {
        if (discountCard == null && balance == null) {
            return null;

        } else {
            BalancedDiscountCard.Builder balancedDiscountCard = BalancedDiscountCard.builder();

            if (discountCard != null) {
                balancedDiscountCard
                        .discountPercentage(discountCard.getDiscountAmount())
                        .number(discountCard.getNumber());
            }

            return balancedDiscountCard.balance(balance).build();
        }
    }

    public BalancedDiscountCard toDefaultDiscountCard(DiscountCardDto discountCardDto) {
        if (discountCardDto == null) {
            return null;

        } else {
            return BalancedDiscountCard.builder()
                    .balance(discountCardDto.getBalance())
                    .number(discountCardDto.getNumber())
                    .discountPercentage((byte) 2)
                    .build();
        }
    }

    public BalancedDiscountCard toNoneDiscountCard(DiscountCardDto discountCardDto) {
        if (discountCardDto == null) {
            return null;

        } else {
            return BalancedDiscountCard.builder()
                    .balance(discountCardDto.getBalance())
                    .number(discountCardDto.getNumber())
                    .discountPercentage((byte) 0)
                    .build();
        }
    }
}