package main.java.ru.clevertec.check.repository.impl;

import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.repository.DiscountCardRepository;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DiscountCardRepositoryImpl implements DiscountCardRepository {

    private final Map<Long, DiscountCard> discountCardMap;

    public DiscountCardRepositoryImpl(Map<Long, DiscountCard> discountCardMap) {
        this.discountCardMap = discountCardMap;
    }

    @Override
    public Optional<DiscountCard> findByNumber(Integer number) {
        return discountCardMap.values().stream()
                .filter(card -> Objects.equals(card.getNumber(), number))
                .findFirst();
    }
}