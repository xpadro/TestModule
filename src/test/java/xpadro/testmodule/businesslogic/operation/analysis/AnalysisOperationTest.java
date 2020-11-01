package xpadro.testmodule.businesslogic.operation.analysis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xpadro.testmodule.businesslogic.operation.OperationHandler;
import xpadro.testmodule.businesslogic.operation.OperationType;
import xpadro.testmodule.businesslogic.test.BiochemistryTest;
import xpadro.testmodule.businesslogic.test.SampleTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnalysisOperationTest {
    private AnalysisOperation operation;

    private OperationHandler handler1;
    private OperationHandler handler2;

    @BeforeEach
    void setUp() {
        handler1 = mock(OperationHandler.class);
        when(handler1.supports(OperationType.ANALYSIS)).thenReturn(true);
        handler2 = mock(OperationHandler.class);
        when(handler2.supports(OperationType.ANALYSIS)).thenReturn(true);

        List<OperationHandler> handlers = Arrays.asList(handler1, handler2);
        operation = new AnalysisOperation(handlers);
    }

    @Test
    public void doesNotSupportOtherOperations() {
        assertFalse(operation.supports(OperationType.CALCULATION));
    }

    @Test
    public void supportsCalculationOperations() {
        assertTrue(operation.supports(OperationType.ANALYSIS));
    }

    @Test
    public void testBatteryFailsIfNoHandlersFound() {
        SampleTest test = new BiochemistryTest("glucose", 0.1f, 0.1f, 0.1f);
        when(handler1.canHandle(test)).thenReturn(false);
        when(handler2.canHandle(test)).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> operation.operate(Collections.singletonList(test)));
    }
}