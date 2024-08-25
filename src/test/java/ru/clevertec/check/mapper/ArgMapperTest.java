package ru.clevertec.check.mapper;

import org.junit.jupiter.api.Test;
import ru.clevertec.check.dto.request.Bucket;

import static org.assertj.core.api.Assertions.assertThat;

class ArgMapperTest {

    private final ArgMapper argMapper = new ArgMapper();

    @Test
    void shouldParseArgsToBucketCorrectly() {
        // given
        String[] args = {"1-10", "2-20", "discountCard=1111", "balanceDebitCard=1000.00"};

        // when
        Bucket bucket = argMapper.parseArg(args);

        // then
        assertThat(bucket).isNotNull();
        assertThat(bucket.products()).hasSize(2);
    }
}