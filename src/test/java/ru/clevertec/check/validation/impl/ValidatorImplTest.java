package ru.clevertec.check.validation.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.exception.ValidationException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatorImplTest {

    private ValidatorImpl validator;

    @BeforeEach
    void setUp() {
        validator = new ValidatorImpl();
    }

    @Test
    void shouldThrowValidationExceptionWhenNoProductsExist() {
        // given
        String[] args = {"balanceDebitCard=100"};

        // when, then
        assertThatThrownBy(() -> validator.validate(args))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void shouldThrowValidationExceptionWhenArgumentsNotValid() {
        // given
        String[] args = {"1-10", "discountCard=1234", "balanceDebitCard=100", "extraInvalidArg"};

        // when, then
        assertThatThrownBy(() -> validator.validate(args))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void shouldThrowValidationExceptionWhenPathArgumentsNotValid() {
        // given
        String[] args = {"1-10", "discountCard=1234", "balanceDebitCard=100", "extraInvalidArg"};

        // when, then
        assertThatThrownBy(() -> validator.validatePathArgs(args))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    void shouldThrowValidationExceptionWhenBalanceNotExist() {
        // given
        String[] args = {"1-10", "discountCard=1234", "balanceDebitCard=qwerty", "extraInvalidArg"};

        // when, then
        assertThatThrownBy(() -> validator.validate(args))
                .isInstanceOf(ValidationException.class);
    }
}