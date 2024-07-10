package ru.clevertec.check.util.dbconnection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DatabaseConnectionTest {

    private Map<String, String> properties;

    @BeforeEach
    void setUp() {
        properties = new HashMap<>();
        properties.put("url", "jdbc:postgresql://localhost:5432/check");
        properties.put("username", "postgres");
        properties.put("password", "87654321");
    }

    @Test
    void testInitializeDatabase_ValidInput() {
        // given
        Connection connection = DatabaseConnection.initializeDatabase(properties);

        // when, then
        assertThat(connection).isNotNull();
    }

    @Test
    void testInitializeDatabase_InvalidInput() {
        // given
        properties.put("url", "not valid url");

        // Act & Assert
        assertThatThrownBy(() -> DatabaseConnection.initializeDatabase(properties))
                .isInstanceOf(RuntimeException.class);
    }
}