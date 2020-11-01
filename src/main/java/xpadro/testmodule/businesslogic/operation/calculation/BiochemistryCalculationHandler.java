package xpadro.testmodule.businesslogic.operation.calculation;

import org.springframework.stereotype.Component;
import xpadro.testmodule.businesslogic.operation.OperationHandler;
import xpadro.testmodule.businesslogic.operation.OperationType;
import xpadro.testmodule.businesslogic.test.BiochemistryTest;
import xpadro.testmodule.businesslogic.test.SampleTest;

@Component
public class BiochemistryCalculationHandler implements OperationHandler {
    private static final float THRESHOLD = 0.6f;

    @Override
    public boolean supports(OperationType type) {
        return OperationType.CALCULATION.equals(type);
    }

    @Override
    public boolean canHandle(SampleTest test) {
        return test.getClass().isAssignableFrom(BiochemistryTest.class);
    }

    @Override
    public CalculationResult handle(SampleTest test) {
        BiochemistryTest bioTest = (BiochemistryTest) test;
        boolean belowThreshold = bioTest.getHbA1cLevel() < THRESHOLD;

        return new CalculationResult(true, belowThreshold);
    }
}
