package ru.clevertec.check.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.dto.response.Printable;
import ru.clevertec.check.exception.FileCreationException;
import ru.clevertec.check.exception.PrintableException;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PrintServiceImplTest {

    @InjectMocks
    private PrintServiceImpl printService;

    @Mock
    private Printable printable;

    private static final Path PATH = Paths.get("testfile.txt");

    @Test
    void shouldPrintToFileMethodSuccess() throws IOException {
        // given
        doNothing().when(printable).print(any(Writer.class));

        // when
        printService.printToFile(PATH, printable);

        // then
        verify(printable, times(1)).print(any(Writer.class));
    }

    @Test
    void shouldThrowPrintableExceptionWhenFilePrint() throws IOException {
        // given
        doThrow(IOException.class).when(printable).print(any(Writer.class));

        // when, then
        assertThatThrownBy(() -> printService.printToFile(PATH, printable))
                .isInstanceOf(PrintableException.class);
    }

    @Test
    void shouldReturnFileCreationException() {
        // given
        Path path = Paths.get("");

        // when, then
        assertThatThrownBy(() -> printService.printToFile(path, printable))
                .isInstanceOf(FileCreationException.class);
    }

    @Test
    void shouldPrintToConsoleMethodSuccess() throws IOException {
        // given
        doNothing().when(printable).print(any(Writer.class));

        // when
        printService.printToConsole(printable);

        // then
        verify(printable, times(1)).print(any(Writer.class));
    }

    @Test
    void shouldReturnFileCreationExceptionWhenConsolePrint() throws IOException {
        // given
        doThrow(IOException.class).when(printable).print(any(Writer.class));

        // when, then
        assertThatThrownBy(() -> printService.printToConsole(printable))
                .isInstanceOf(PrintableException.class);
    }
}