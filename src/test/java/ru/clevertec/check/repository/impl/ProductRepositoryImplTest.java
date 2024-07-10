package ru.clevertec.check.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.exception.NotFoundException;
import ru.clevertec.check.model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {

    @InjectMocks
    private ProductRepositoryImpl productRepository;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        when(connection.prepareStatement(ProductRepositoryImpl.SELECT_PRODUCT_BY_ID))
                .thenReturn(preparedStatement);
    }

    @Test
    void shouldFindAllById() throws SQLException {
        // given
        Long[] ids = {1L, 2L};
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getLong("id")).thenReturn(ids[0]).thenReturn(ids[1]);
        when(resultSet.getString("description")).thenReturn("Product 1").thenReturn("Product 2");
        when(resultSet.getBigDecimal("price")).thenReturn(BigDecimal.TWO).thenReturn(BigDecimal.TEN);
        when(resultSet.getInt("quantity_in_stock")).thenReturn(5).thenReturn(6);
        when(resultSet.getBoolean("wholesale_product")).thenReturn(true).thenReturn(true);

        // when
        List<Product> products = productRepository.findAllById(Arrays.asList(ids));

        // then
        assertThat(products).hasSize(2);
        assertThat(products.get(0).getId()).isEqualTo(ids[0]);
        assertThat(products.get(1).getId()).isEqualTo(ids[1]);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenProductNotFound() throws SQLException {
        // given
        Long id = 1L;
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        // when, then
        assertThatThrownBy(() -> productRepository.findAllById(Arrays.asList(id)))
                .isInstanceOf(NotFoundException.class);
    }
}