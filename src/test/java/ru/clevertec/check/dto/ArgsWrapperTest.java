package ru.clevertec.check.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ArgsWrapperTest {

    private ArgsWrapper argsWrapper;

    @BeforeEach
    void setUp() {
        argsWrapper = new ArgsWrapper();
    }

    @Test
    void shouldAddAppArgs() {
        // given
        String[] args = {"arg1", "arg2"};

        // when
        argsWrapper.addAppArg(args);

        // then
        assertThat(argsWrapper.getAppArguments()).containsExactly("arg1", "arg2");
    }

    @Test
    void shouldReturnAppArguments() {
        // given
        argsWrapper.addAppArg("arg1", "arg2");

        // when
        String[] appArguments = argsWrapper.getAppArguments();

        // then
        assertThat(appArguments).containsExactly("arg1", "arg2");
    }

    @Test
    void shouldReturnProperties() {
        // given
        argsWrapper.putProperty("key1", "value1");
        argsWrapper.putProperty("key2", "value2");

        // when
        Map<String, String> properties = argsWrapper.getProperties();

        // then
        assertThat(properties).containsEntry("key1", "value1")
                .containsEntry("key2", "value2");
    }

    @Test
    void shouldPutProperty() {
        // given, when
        argsWrapper.putProperty("key", "value");

        // then
        assertThat(argsWrapper.getProperties()).containsEntry("key", "value");
    }

    @Test
    void shouldReturnSaveToFilePath() {
        // given
        argsWrapper.putProperty(ArgsWrapper.SAVE_TO_FILE_PATH, "path/to/file");

        // when
        String saveToFilePath = argsWrapper.getSaveToFilePath();

        // then
        assertThat(saveToFilePath).isEqualTo("path/to/file");
    }

    @Test
    void shouldReturnNullWhenSaveToFilePathNotSet() {
        // given, when
        String saveToFilePath = argsWrapper.getSaveToFilePath();

        // then
        assertThat(saveToFilePath).isNull();
    }
}