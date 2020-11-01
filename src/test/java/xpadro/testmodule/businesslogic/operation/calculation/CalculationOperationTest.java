package xpadro.testmodule.businesslogic.operation.calculation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xpadro.testmodule.businesslogic.operation.OperationHandler;
import xpadro.testmodule.businesslogic.operation.OperationResult;
import xpadro.testmodule.businesslogic.operation.OperationType;
import xpadro.testmodule.businesslogic.test.BiochemistryTest;
import xpadro.testmodule.businesslogic.test.SampleTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CalculationOperationTest {
    private CalculationOperation operation;

    private OperationHandler handler1;
    private OperationHandler handler2;

    @BeforeEach
    void setUp() {
        handler1 = mock(OperationHandler.class);
        when(handler1.supports(OperationType.CALCULATION)).thenReturn(true);
        handler2 = mock(OperationHandler.class);
        when(handler2.supports(OperationType.CALCULATION)).thenReturn(true);

        List<OperationHandler> handlers = Arrays.asList(handler1, handler2);
        operation = new CalculationOperation(handlers);
    }

    @Test
    public void doesNotSupportOtherOperations() {
        assertFalse(operation.supports(OperationType.ANALYSIS));
    }

    @Test
    public void supportsCalculationOperations() {
        assertTrue(operation.supports(OperationType.CALCULATION));
    }

    @Test
    public void testBatteryFailsIfNoHandlersFound() {
        SampleTest test = buildTest();
        when(handler1.canHandle(test)).thenReturn(false);
        when(handler2.canHandle(test)).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> operation.operate(Collections.singletonList(test)));
    }

    @Test
    public void sampleTestIsOperatedByASingleOperation() {
        SampleTest test = buildTest();
        when(handler1.canHandle(test)).thenReturn(true);
        OperationResult result = new CalculationResult(true, true);
        when(handler1.handle(test)).thenReturn(result);
        when(handler2.canHandle(test)).thenReturn(true);

        List<OperationResult> results = operation.operate(Collections.singletonList(test));

        assertThat(results.size(), equalTo(1));
        assertSame(result, results.get(0));
        verify(handler2, never()).handle(test);
    }

    private SampleTest buildTest() {
        return new BiochemistryTest("glucose", 0.1f, 0.1f, 0.1f);
    }
}