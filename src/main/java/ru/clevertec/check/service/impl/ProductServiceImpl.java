package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.dto.ProductInfo;
import main.java.ru.clevertec.check.dto.request.ProductDto;
import main.java.ru.clevertec.check.exception.NotFoundException;
import main.java.ru.clevertec.check.exception.QuantityInStockIsNotAvailableException;
import main.java.ru.clevertec.check.mapper.ProductMapper;
import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.repository.ProductRepository;
import main.java.ru.clevertec.check.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    /**
     * Метод уменьшает количество продуктов в DB и возвращает обработанный продукт
     *
     * @param productDtoList Список идентификаторов продуктов с количеством
     * @return Список с информацией по продуктам
     */
    @Override
    public List<ProductInfo> subtractCountAndGet(List<ProductDto> productDtoList) {
        Map<Long, Integer> idCountMap = productDtoList.stream()
                .collect(Collectors.toMap(ProductDto::id, ProductDto::quantity));

        List<Product> allById = productRepository.findAllById(idCountMap.keySet());

        if (allById.size() != idCountMap.size()) {
            throw new NotFoundException();
        }

        return allById.stream()
                .map(product -> updateQuantityInStock(product, idCountMap.get(product.getId())))
                .map(productRepository::saveAndFlush)
                .map(product -> productMapper.toGoodInfo(product, idCountMap.get(product.getId())))
                .toList();
    }

    private Product updateQuantityInStock(Product product, Integer orderCount) {
        int remain = product.getQuantityInStock() - orderCount;

        if (remain < 0) {
            throw new QuantityInStockIsNotAvailableException();
        }
        product.setQuantityInStock(remain);
        return product;
    }
}