package xpadro.testmodule.businesslogic.operation.analysis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xpadro.testmodule.MockSampleTest;
import xpadro.testmodule.businesslogic.operation.OperationType;
import xpadro.testmodule.businesslogic.test.BiochemistryTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BiochemistryAnalysisHandlerTest {
    private BiochemistryAnalysisHandler handler;

    @BeforeEach
    void setUp() {
        handler = new BiochemistryAnalysisHandler();
    }

    @Test
    public void doesNotSupportOtherOperationTypes() {
        assertFalse(handler.supports(OperationType.CALCULATION));
    }

    @Test
    public void supportsAnalysisOperations() {
        assertTrue(handler.supports(OperationType.ANALYSIS));
    }

    @Test
    public void canNotHandleOtherTestTypes() {
        assertFalse(handler.canHandle(new MockSampleTest()));
    }

    @Test
    public void biochemistryTestsAreHandled() {
        assertTrue(handler.canHandle(new BiochemistryTest("glucose", 0.3f, 0.3f, 0.2f)));
    }

    @Test
    public void detectsAssociationBetweenPeriodAndHbA1cLevel() {
        BiochemistryTest positiveTest = new BiochemistryTest("glucose", 0.3f, 0.3f, 0.2f);
        assertTrue(handler.handle(positiveTest).isAssociated());

        BiochemistryTest negativeTest = new BiochemistryTest("glucose", 0.3f, 0.1f, 0.2f);
        assertFalse(handler.handle(negativeTest).isAssociated());
    }
}