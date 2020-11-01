package xpadro.testmodule.businesslogic.operation.calculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xpadro.testmodule.businesslogic.operation.AbstractOperation;
import xpadro.testmodule.businesslogic.operation.OperationHandler;
import xpadro.testmodule.businesslogic.operation.OperationType;

import java.util.List;

@Component
public class CalculationOperation extends AbstractOperation {

    @Autowired
    public CalculationOperation(List<OperationHandler> handlers) {
        super(handlers);
    }

    @Override
    protected OperationType getType() {
        return OperationType.CALCULATION;
    }
}
