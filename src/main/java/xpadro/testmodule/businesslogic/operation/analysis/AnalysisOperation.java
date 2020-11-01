package xpadro.testmodule.businesslogic.operation.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xpadro.testmodule.businesslogic.operation.AbstractOperation;
import xpadro.testmodule.businesslogic.operation.OperationHandler;
import xpadro.testmodule.businesslogic.operation.OperationType;

import java.util.List;

@Component
public class AnalysisOperation extends AbstractOperation {

    @Autowired
    protected AnalysisOperation(List<OperationHandler> handlers) {
        super(handlers);
    }

    @Override
    protected OperationType getType() {
        return OperationType.ANALYSIS;
    }
}
