package ru.clevertec.check.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.model.DiscountCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountCardRepositoryImplTest {

    @InjectMocks
    private DiscountCardRepositoryImpl discountCardRepository;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        when(connection.prepareStatement(DiscountCardRepositoryImpl.SELECT_DISCOUNT_CARD_BY_ID))
                .thenReturn(preparedStatement);
    }

    @Test
    void shouldFindDiscountCardByNumber() throws SQLException {
        // given
        int cardNumber = 123;
        when(preparedStatement.executeQuery())
                .thenReturn(resultSet);
        when(resultSet.next())
                .thenReturn(true);
        when(resultSet.getLong("id"))
                .thenReturn(1L);
        when(resultSet.getByte("amount"))
                .thenReturn((byte) 10);
        when(resultSet.getInt("number"))
                .thenReturn(cardNumber);

        // when
        Optional<DiscountCard> result = discountCardRepository.findByNumber(cardNumber);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getNumber()).isEqualTo(cardNumber);
    }

    @Test
    void shouldReturnEmptyOptionalWhenCardNotFound() throws SQLException {
        // given
        when(preparedStatement.executeQuery())
                .thenReturn(resultSet);
        when(resultSet.next())
                .thenReturn(false);

        // when
        Optional<DiscountCard> result = discountCardRepository.findByNumber(123);

        // then
        assertThat(result).isNotPresent();
    }
}