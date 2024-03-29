package xpadro.testmanager.domain.operation.calculation;

import xpadro.testmanager.domain.operation.OperationResult;

public class CalculationResult extends OperationResult {
    private static final long serialVersionUID = -8010067173850927195L;
    private final boolean belowThreshold;

    public CalculationResult(boolean processed, boolean belowThreshold) {
        super(processed);
        this.belowThreshold = belowThreshold;
    }

    public boolean isBelowThreshold() {
        return belowThreshold;
    }
}
