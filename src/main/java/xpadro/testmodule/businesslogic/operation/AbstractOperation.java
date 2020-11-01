package xpadro.testmodule.businesslogic.operation;

import xpadro.testmodule.businesslogic.test.SampleTest;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractOperation implements Operation {
    private final List<OperationHandler> operationHandlers;

    protected AbstractOperation(List<OperationHandler> handlers) {
        this.operationHandlers = handlers.stream()
                .filter(handler -> handler.supports(getType()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean supports(OperationType type) {
        return getType().equals(type);
    }

    protected abstract OperationType getType();

    @Override
    public List<OperationResult> operate(List<SampleTest> tests) {
        return tests.stream()
                .map(this::handle)
                .collect(Collectors.toList());
    }

    private OperationResult handle(SampleTest test) {
        return operationHandlers.stream()
                .filter(handler -> handler.canHandle(test))
                .findFirst()
                .map(handler -> handler.handle(test))
                .orElseThrow(IllegalStateException::new);
    }
}
