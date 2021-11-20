package xpadro.testmanager.domain.operation;

import xpadro.testmanager.domain.operation.calculation.Calculation;
import xpadro.testmanager.domain.test.SampleTest;

import java.util.List;

public class CalculationOperation implements Operation {
    private final List<Calculation> calculations;

    public CalculationOperation(List<Calculation> calculations) {
        this.calculations = calculations;
    }

    @Override
    public OperationResult operate(SampleTest test) {
        return calculations.stream()
                .filter(calc -> calc.supports(test))
                .findAny()
                .map(c -> c.calculate(test))
                .orElseThrow(RuntimeException::new);
    }
}
