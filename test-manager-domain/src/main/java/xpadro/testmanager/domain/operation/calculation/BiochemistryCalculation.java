package xpadro.testmanager.domain.operation.calculation;

import xpadro.testmanager.domain.operation.OperationResult;
import xpadro.testmanager.domain.test.SampleTest;
import xpadro.testmanager.domain.test.impl.BiochemistryTest;

public class BiochemistryCalculation implements Calculation {
    private static final float THRESHOLD = 0.6f;

    @Override
    public boolean supports(SampleTest test) {
        return test.getClass().isAssignableFrom(BiochemistryTest.class);
    }

    @Override
    public OperationResult calculate(SampleTest test) {
        BiochemistryTest bioTest = (BiochemistryTest) test;
        boolean belowThreshold = bioTest.getHbA1cLevel() < THRESHOLD;

        return new CalculationResult(true, belowThreshold);
    }

}
