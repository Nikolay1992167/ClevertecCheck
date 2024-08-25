package ru.clevertec.check.factory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.dto.BalancedDiscountCard;
import ru.clevertec.check.dto.ProductInfo;
import ru.clevertec.check.dto.response.Check;
import ru.clevertec.check.exception.BalanceNotAvailableException;
import ru.clevertec.check.factory.impl.CheckFactoryImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckFactoryImplTest {

    @InjectMocks
    private CheckFactoryImpl checkFactory;

    @Mock
    private BalancedDiscountCard discountCard;

    private static final List<ProductInfo> productInfoList = Arrays.asList(
            new ProductInfo("Product 1", new BigDecimal("10"), 2, false),
            new ProductInfo("Product 2", new BigDecimal("20"), 5, true)
    );

    @Test
    void shouldCreateCheck() {
        // given
        when(discountCard.discountPercentage()).thenReturn((byte) 5);
        when(discountCard.balance()).thenReturn(new BigDecimal("1000"));
        when(discountCard.isDefault()).thenReturn(true);

        // when
        Check check = checkFactory.createCheck(productInfoList, discountCard);

        // then
        assertThat(check).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenDiscountCardBalance0() {
        // given
        when(discountCard.balance()).thenReturn(new BigDecimal("0"));

        // when, then
        assertThatThrownBy(() -> checkFactory.createCheck(productInfoList, discountCard))
                .isInstanceOf(BalanceNotAvailableException.class);
    }

    @Test
    void shouldCreateCheckWhenDiscountCardIsDefault() {
        // given
        when(discountCard.discountPercentage()).thenReturn((byte) 5);
        when(discountCard.balance()).thenReturn(new BigDecimal("1000"));
        when(discountCard.isDefault()).thenReturn(false);

        // when
        Check check = checkFactory.createCheck(productInfoList, discountCard);

        // then
        assertThat(check).isNotNull();
    }
}