package ru.clevertec.check.repository.impl;

import ru.clevertec.check.exception.DataException;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.repository.DiscountCardRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DiscountCardRepositoryImpl implements DiscountCardRepository {

    private final Connection connection;

    public static final String SELECT_DISCOUNT_CARD_BY_ID = "SELECT * FROM discount_card WHERE id = ?";

    public DiscountCardRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<DiscountCard> findByNumber(Integer number) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_DISCOUNT_CARD_BY_ID)
        ) {
            statement.setLong(1, number);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    DiscountCard discountCard = DiscountCard.builder()
                            .id(resultSet.getLong("id"))
                            .discountAmount(resultSet.getByte("amount"))
                            .number(resultSet.getInt("number"))
                            .build();
                    return Optional.of(discountCard);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new DataException();
        }
    }
}