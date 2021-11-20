package xpadro.testmanager.domain.order;

import xpadro.testmanager.domain.operation.Operation;
import xpadro.testmanager.domain.test.SampleTest;

import java.util.List;

public class TestRequest {
    private final SampleTest test;
    private final List<Operation> operations;

    public TestRequest(SampleTest test, List<Operation> operations) {
        this.test = test;
        this.operations = operations;
    }

    public SampleTest getTest() {
        return test;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
