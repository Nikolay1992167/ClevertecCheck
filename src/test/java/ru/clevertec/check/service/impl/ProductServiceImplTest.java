package ru.clevertec.check.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.dto.ProductInfo;
import ru.clevertec.check.dto.request.ProductDto;
import ru.clevertec.check.exception.NotFoundException;
import ru.clevertec.check.exception.QuantityInStockIsNotAvailableException;
import ru.clevertec.check.mapper.ProductMapper;
import ru.clevertec.check.mapper.impl.ProductMapperImpl;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.repository.ProductRepository;
import util.ProductTestData;
import util.ProductsDtoTestData;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        productMapper = new ProductMapperImpl();
        productService = new ProductServiceImpl(productRepository, productMapper);
    }

    @Test
    void shouldSubtractCountAndGetProductInfoList() {
        // given
        List<ProductDto> productDtoList = ProductsDtoTestData.getListProductDto();
        Map<Long, Integer> idCountMap = productDtoList.stream()
                .collect(Collectors.toMap(ProductDto::id, ProductDto::quantity));
        List<Product> allById = ProductTestData.getListProductsAll();

        when(productRepository.findAllById(idCountMap.keySet())).thenReturn(allById);

        // when
        List<ProductInfo> actualProductInfos = productService.subtractCountAndGet(productDtoList);

        // then
        assertThat(actualProductInfos).isNotEmpty();
    }

    @Test
    void shouldThrowNotFoundExceptionWhenProductNotFound() {
        // given
        List<ProductDto> productDtoList = ProductsDtoTestData.getListProductDto();
        when(productRepository.findAllById(anySet())).thenReturn(List.of());

        // when, then
        assertThatThrownBy(() -> productService.subtractCountAndGet(productDtoList))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void shouldThrowQuantityInStockIsNotAvailableExceptionWhenOrderCountExceedsStock() {
        // given
        List<ProductDto> productDtoList = ProductsDtoTestData.getListProductDto();
        List<Product> listProducts = ProductTestData.getListProducts();
        when(productRepository.findAllById(anySet())).thenReturn(listProducts);
        // when, then
        assertThatThrownBy(() -> productService.subtractCountAndGet(productDtoList))
                .isInstanceOf(QuantityInStockIsNotAvailableException.class);
    }
}