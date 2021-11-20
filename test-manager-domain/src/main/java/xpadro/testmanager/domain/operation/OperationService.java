package xpadro.testmanager.domain.operation;

import xpadro.testmanager.domain.order.Order;
import xpadro.testmanager.domain.order.TestRequest;
import xpadro.testmanager.domain.port.OperationPort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OperationService implements OperationPort {
    private final Map<OperationType, Operation> operations;

    public OperationService(Map<OperationType, Operation> operations) {
        this.operations = operations;
    }

    @Override
    public List<OperationResult> processOrder(Order order) {
        return order.getTestRequests().stream()
                .flatMap(request -> performRequest(request).stream())
                .collect(Collectors.toList());
    }

    private List<OperationResult> performRequest(TestRequest testRequest) {
        return testRequest.getOperationTypes().stream()
                .map(operation -> runOperation(testRequest, operation))
                .collect(Collectors.toList());
    }

    private OperationResult runOperation(TestRequest testRequest, OperationType operationType) {
        Operation operation = operations.get(operationType);

        if (operation == null) {
            throw new IllegalStateException();
        }

        return operation.operate(testRequest.getTest());
    }
}
