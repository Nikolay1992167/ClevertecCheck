package main.java.ru.clevertec.check.repository.impl;

import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> goodMap;

    public ProductRepositoryImpl(Map<Long, Product> goodMap) {
        this.goodMap = goodMap;
    }

    @Override
    public Product saveAndFlush(Product good) {
        if (good.getId() == null) {
            Long id = goodMap.keySet().stream()
                    .max(Long::compareTo)
                    .orElse(1L);
            good.setId(id);
        }

        goodMap.put(good.getId(), good);

        return new Product(good);
    }

    @Override
    public List<Product> findAllById(Iterable<Long> longs) {
        List<Product> result = new ArrayList<>();

        longs.forEach(id ->
                result.add(goodMap.get(id))
        );

        return result.stream()
                .filter(Objects::nonNull)
                .map(Product::new)
                .toList();
    }
}