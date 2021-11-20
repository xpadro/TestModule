package xpadro.testmanager.domain.operation;

import xpadro.testmanager.domain.operation.analysis.Analysis;
import xpadro.testmanager.domain.test.SampleTest;

import java.util.List;

public class AnalysisOperation implements Operation {
    private final List<Analysis> analyses;

    public AnalysisOperation(List<Analysis> analyses) {
        this.analyses = analyses;
    }

    @Override
    public OperationType getType() {
        return OperationType.ANALYSIS;
    }

    @Override
    public OperationResult operate(SampleTest test) {
        return analyses.stream()
                .filter(analysis -> analysis.supports(test))
                .findAny()
                .map(c -> c.analyse(test))
                .orElseThrow(IllegalStateException::new);
    }
}
