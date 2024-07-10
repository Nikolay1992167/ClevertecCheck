package ru.clevertec.check.starter.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.check.controller.MainOrderController;
import ru.clevertec.check.dto.request.Bucket;
import ru.clevertec.check.dto.response.Check;
import ru.clevertec.check.exception.FileCreationException;
import ru.clevertec.check.mapper.ArgMapper;
import ru.clevertec.check.service.PrintService;
import ru.clevertec.check.validation.Validator;
import util.BucketTestData;
import util.CheckTestData;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainOrderImplTest {
    @InjectMocks
    private MainOrderImpl mainOrder;

    @Mock
    private ArgMapper argMapper;

    @Mock
    private PrintService printService;

    @Mock
    private MainOrderController orderController;

    @Mock
    private Validator<String[]> validator;

    private final String filePath = "testFilePath";

    @BeforeEach
    void setUp() {
        mainOrder = new MainOrderImpl(argMapper, printService, orderController, validator, filePath);
    }

    @Test
    void shouldCheckProcessOrderWhenArgsAreValid() {
        // given
        String[] args = {"validArg1", "validArg2"};
        Bucket bucket = BucketTestData.getBucket();
        Check check = CheckTestData.getCheck();
        Path path = Paths.get(filePath);

        when(argMapper.parseArg(args)).thenReturn(bucket);
        when(orderController.createCheck(bucket)).thenReturn(check);

        // when
        mainOrder.processOrder(args);

        // then
        verify(validator).validate(args);
        verify(argMapper).parseArg(args);
        verify(orderController).createCheck(bucket);
        verify(printService).printToFile(path, check);
        verify(printService).printToConsole(check);
    }

    @Test
    void shouldHandlePrintableExceptionWhenThrown() {
        // given
        String[] args = {"invalidArg1"};
        FileCreationException printableException = mock(FileCreationException.class);
        Path path = Paths.get(filePath);

        doThrow(printableException).when(validator).validate(args);

        // when
        mainOrder.processOrder(args);

        // then
        verify(printService).printToFile(eq(path), any(FileCreationException.class));
        verify(printService).printToConsole(printableException);
    }
}