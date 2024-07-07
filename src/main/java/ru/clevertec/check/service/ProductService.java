package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.dto.ProductInfo;
import main.java.ru.clevertec.check.dto.request.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductInfo> subtractCountAndGet(List<ProductDto> productDtoList);
}