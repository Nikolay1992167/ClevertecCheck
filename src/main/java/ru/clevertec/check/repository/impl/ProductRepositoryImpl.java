package ru.clevertec.check.repository.impl;

import ru.clevertec.check.exception.DataException;
import ru.clevertec.check.exception.NotFoundException;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    private final Connection connection;

    public static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";

    public ProductRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> findAllById(Iterable<Long> longs) {

        List<Product> result = new ArrayList<>();

        longs.forEach(id ->
                result.add(findByNumber(id).orElseThrow(NotFoundException::new))
        );
        return result;
    }

    private Optional<Product> findByNumber(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)
        ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = Product.builder()
                            .id(resultSet.getLong("id"))
                            .description(resultSet.getString("description"))
                            .price(resultSet.getBigDecimal("price"))
                            .quantityInStock(resultSet.getInt("quantity_in_stock"))
                            .wholesaleProduct(resultSet.getBoolean("wholesale_product"))
                            .build();
                    return Optional.of(product);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new DataException();
        }
    }
}