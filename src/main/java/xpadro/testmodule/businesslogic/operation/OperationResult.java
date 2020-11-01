package xpadro.testmodule.businesslogic.operation;

import java.io.Serializable;

public class OperationResult implements Serializable {
    private static final long serialVersionUID = -451084623909909747L;
    private final boolean processed;

    public OperationResult(boolean processed) {
        this.processed = processed;
    }

    public boolean isProcessed() {
        return processed;
    }
}
