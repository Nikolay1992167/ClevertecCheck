package ru.clevertec.check.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.dto.BalancedDiscountCard;
import ru.clevertec.check.dto.request.DiscountCardDto;
import ru.clevertec.check.mapper.DiscountCardMapper;
import ru.clevertec.check.mapper.impl.DiscountCardMapperImpl;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.repository.DiscountCardRepository;
import util.DiscountCardDtoTestData;
import util.DiscountCardTestData;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountCardServiceImplTest {

    @InjectMocks
    private DiscountCardServiceImpl discountCardService;

    @Mock
    private DiscountCardRepository discountCardRepository;

    private DiscountCardMapper discountCardMapper;

    @BeforeEach
    void setUp() {
        discountCardMapper = new DiscountCardMapperImpl();
        discountCardService = new DiscountCardServiceImpl(discountCardRepository, discountCardMapper);
    }

    @Test
    void shouldReturnNoneDiscountCardWhenCardNumberIsNull() {
        // given
        DiscountCardDto discountCardDto = DiscountCardDtoTestData.getDiscountCardDtoWithOutNumber();

        // when
        BalancedDiscountCard actual = discountCardService.getWithBalance(discountCardDto);

        // then
        assertThat(actual).isNotNull();
    }

    @Test
    void shouldReturnDefaultDiscountCardWhenCardNumberIsNotInDB() {
        // given
        Integer cardNumber = 3333;
        DiscountCardDto discountCardDto = new DiscountCardDto(String.valueOf(cardNumber), String.valueOf(100));
        when(discountCardRepository.findByNumber(cardNumber))
                .thenReturn(Optional.empty());

        // when
        BalancedDiscountCard result = discountCardService.getWithBalance(discountCardDto);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void shouldReturnBalancedDiscountCardWhenCardNumberIsInDB() {
        // given
        DiscountCardDto discountCardDto = DiscountCardDtoTestData.getDiscountCardDto();
        DiscountCard discountCard = DiscountCardTestData.getDiscountCard();
        when(discountCardRepository.findByNumber(discountCard.getNumber()))
                .thenReturn(Optional.of(discountCard));

        // when
        BalancedDiscountCard result = discountCardService.getWithBalance(discountCardDto);

        // then
        assertThat(result).isNotNull();
    }
}