package xpadro.testmodule.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xpadro.testmodule.businesslogic.operation.Operation;
import xpadro.testmodule.businesslogic.operation.OperationResult;
import xpadro.testmodule.businesslogic.operation.OperationType;
import xpadro.testmodule.businesslogic.order.Order;
import xpadro.testmodule.businesslogic.port.HandleResultsPort;
import xpadro.testmodule.businesslogic.port.PerformTestsPort;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerformTestsService implements PerformTestsPort {
    private final List<Operation> operations;
    private final HandleResultsPort handleResultsPort;

    @Autowired
    public PerformTestsService(List<Operation> operations, HandleResultsPort handleResultsPort) {
        this.operations = operations;
        this.handleResultsPort = handleResultsPort;
    }

    @Override
    public void execute(Order order) {
        List<OperationResult> results = order.getOperationTypes().stream()
                .map(this::getOperation)
                .flatMap(operation -> operation.operate(order.getTests()).stream())
                .collect(Collectors.toList());

        handleResultsPort.handle(results);
    }

    private Operation getOperation(OperationType type) {
        return operations.stream()
                .filter(operation -> operation.supports(type))
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }
}
