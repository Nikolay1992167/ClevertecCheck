package ru.clevertec.check.service.impl;

import ru.clevertec.check.dto.BalancedDiscountCard;
import ru.clevertec.check.dto.ProductInfo;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.dto.request.ProductDto;
import ru.clevertec.check.dto.response.Check;
import ru.clevertec.check.factory.CheckFactory;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.OrderService;
import ru.clevertec.check.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private final ProductService productService;
    private final DiscountCardService discountCardService;
    private final CheckFactory checkFactory;

    public OrderServiceImpl(ProductService productService,
                            DiscountCardService discountCardService,
                            CheckFactory checkFactory) {
        this.productService = productService;
        this.discountCardService = discountCardService;
        this.checkFactory = checkFactory;
    }

    /**
     * Метод, который делегирует обработку {@link ru.clevertec.check.model.Product} в {@link ProductService}
     * и получает информацию о карте из {@link DiscountCardService}
     * и делегирует создание чека в {@link CheckFactory}
     *
     * @param bucket Список покупок с номером карты и балансом
     * @return Готовый чек
     */
    @Override
    public Check generateCheck(Bucket bucket) {
        List<ProductDto> productDtos = bucket.products().stream()
                .collect(Collectors.collectingAndThen(Collectors.groupingBy(
                                ProductDto::id, Collectors.reducing(0, ProductDto::quantity, Integer::sum)),
                        idQty -> idQty.entrySet().stream()
                                .map(x -> new ProductDto(x.getKey(), x.getValue())).toList().reversed()));

        List<ProductInfo> productInfos = productService.subtractCountAndGet(productDtos);
        BalancedDiscountCard discountCardDto = discountCardService.getWithBalance(bucket.discountCard());

        return checkFactory.createCheck(productInfos, discountCardDto);
    }
}