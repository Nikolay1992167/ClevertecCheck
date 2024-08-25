package ru.clevertec.check.service.impl;

import ru.clevertec.check.dto.ProductInfo;
import ru.clevertec.check.dto.request.ProductDto;
import ru.clevertec.check.exception.NotFoundException;
import ru.clevertec.check.exception.QuantityInStockIsNotAvailableException;
import ru.clevertec.check.mapper.ProductMapper;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.repository.ProductRepository;
import ru.clevertec.check.service.ProductService;

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