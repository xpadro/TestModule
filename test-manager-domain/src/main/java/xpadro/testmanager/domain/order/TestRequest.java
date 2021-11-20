package xpadro.testmanager.domain.order;

import xpadro.testmanager.domain.operation.OperationType;
import xpadro.testmanager.domain.test.SampleTest;

import java.util.List;

public class TestRequest {
    private final SampleTest test;
    private final List<OperationType> operations;

    public TestRequest(SampleTest test, List<OperationType> operations) {
        this.test = test;
        this.operations = operations;
    }

    public SampleTest getTest() {
        return test;
    }

    public List<OperationType> getOperationTypes() {
        return operations;
    }
}
