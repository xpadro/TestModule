package xpadro.testmanager.domain.operation.calculation;

import xpadro.testmanager.domain.operation.OperationResult;
import xpadro.testmanager.domain.test.SampleTest;

public interface Calculation {

    boolean supports(SampleTest test);

    OperationResult calculate(SampleTest test);
}
