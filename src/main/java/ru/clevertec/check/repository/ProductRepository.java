package main.java.ru.clevertec.check.repository;

import main.java.ru.clevertec.check.model.Product;

import java.util.List;

public interface ProductRepository {

    Product saveAndFlush(Product entity);

    List<Product> findAllById(Iterable<Long> longs);
}