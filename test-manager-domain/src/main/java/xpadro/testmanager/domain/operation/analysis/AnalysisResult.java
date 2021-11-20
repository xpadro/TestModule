package xpadro.testmanager.domain.operation.analysis;

import xpadro.testmanager.domain.operation.OperationResult;

public class AnalysisResult extends OperationResult {
    private static final long serialVersionUID = 4629249530115806710L;
    private final boolean isAssociated;

    public AnalysisResult(boolean processed, boolean isAssociated) {
        super(processed);
        this.isAssociated = isAssociated;
    }

    public boolean isAssociated() {
        return isAssociated;
    }
}
