package ru.clevertec.check.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.dto.BalancedDiscountCard;
import ru.clevertec.check.dto.ProductInfo;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.dto.request.DiscountCardDto;
import ru.clevertec.check.dto.response.Check;
import ru.clevertec.check.factory.CheckFactory;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.ProductService;
import util.BalancedDiscountCardTestData;
import util.BucketTestData;
import util.CheckTestData;
import util.ProductInfoTestData;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private ProductService productService;

    @Mock
    private DiscountCardService discountCardService;

    @Mock
    private CheckFactory checkFactory;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(productService, discountCardService, checkFactory);
    }

    @Test
    void shouldGenerateCheckCorrectly() {
        // given
        Bucket bucket = BucketTestData.getBucket();
        List<ProductInfo> productInfos = ProductInfoTestData.getListProductInfo();
        BalancedDiscountCard balancedDiscountCard = BalancedDiscountCardTestData.getBalancedDiscountCard();
        Check expectedCheck = CheckTestData.getCheck();

        when(productService.subtractCountAndGet(anyList()))
                .thenReturn(productInfos);
        when(discountCardService.getWithBalance(any(DiscountCardDto.class)))
                .thenReturn(balancedDiscountCard);
        when(checkFactory.createCheck(productInfos, balancedDiscountCard))
                .thenReturn(expectedCheck);
        // when
        Check actualCheck = orderService.generateCheck(bucket);

        // then
        assertThat(actualCheck).isEqualTo(expectedCheck);
    }
}