package xpadro.testmanager.domain.operation.calculation;

import xpadro.testmanager.domain.operation.OperationResult;
import xpadro.testmanager.domain.test.SampleTest;
import xpadro.testmanager.domain.test.impl.ImmunologyTest;

public class ImmunologyCalculation implements Calculation {

    @Override
    public boolean supports(SampleTest test) {
        return test.getClass().isAssignableFrom(ImmunologyTest.class);
    }

    @Override
    public OperationResult calculate(SampleTest test) {
        return new CalculationResult(true, true);
    }
}
