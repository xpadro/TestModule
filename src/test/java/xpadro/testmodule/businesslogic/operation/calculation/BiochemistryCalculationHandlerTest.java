package xpadro.testmodule.businesslogic.operation.calculation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xpadro.testmodule.MockSampleTest;
import xpadro.testmodule.businesslogic.operation.OperationType;
import xpadro.testmodule.businesslogic.test.BiochemistryTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BiochemistryCalculationHandlerTest {
    private BiochemistryCalculationHandler handler;

    @BeforeEach
    void setUp() {
        handler = new BiochemistryCalculationHandler();
    }

    @Test
    public void doesNotSupportOtherOperationTypes() {
        assertFalse(handler.supports(OperationType.ANALYSIS));
    }

    @Test
    public void supportsCalculationOperations() {
        assertTrue(handler.supports(OperationType.CALCULATION));
    }

    @Test
    public void canNotHandleOtherTestTypes() {
        assertFalse(handler.canHandle(new MockSampleTest()));
    }

    @Test
    public void biochemistryTestsAreHandled() {
        assertTrue(handler.canHandle(new BiochemistryTest("glucose", 0.1f, 0.1f, 0.1f)));
    }

    @Test
    public void hbA1cLevelIsBelowThreshold() {
        CalculationResult result = handler.handle(new BiochemistryTest("glucose", 0.1f, 0.1f, 0.1f));

        assertTrue(result.isBelowThreshold());
    }

    @Test
    public void hbA1cLevelIsAboveThreshold() {
        CalculationResult result = handler.handle(new BiochemistryTest("glucose", 0.7f, 0.1f, 0.1f));

        assertFalse(result.isBelowThreshold());
    }
}