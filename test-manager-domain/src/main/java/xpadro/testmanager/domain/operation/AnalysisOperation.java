package xpadro.testmanager.domain.operation;

import xpadro.testmanager.domain.operation.analysis.Analysis;
import xpadro.testmanager.domain.test.SampleTest;

import java.util.List;

public class AnalysisOperation implements Operation {
    private final List<Analysis> analysis;

    public AnalysisOperation(List<Analysis> analysis) {
        this.analysis = analysis;
    }

    @Override
    public OperationResult operate(SampleTest test) {
        return analysis.stream()
                .filter(calc -> calc.supports(test))
                .findAny()
                .map(c -> c.analyse(test))
                .orElseThrow(RuntimeException::new);
    }
}
