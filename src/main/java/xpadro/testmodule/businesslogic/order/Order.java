package xpadro.testmodule.businesslogic.order;

import xpadro.testmodule.businesslogic.operation.OperationType;
import xpadro.testmodule.businesslogic.test.SampleTest;

import java.util.List;

public class Order {
    private final List<SampleTest> tests;
    private final List<OperationType> operationTypes;

    public Order(List<SampleTest> tests, List<OperationType> operationTypes) {
        this.tests = tests;
        this.operationTypes = operationTypes;
    }

    public List<SampleTest> getTests() {
        return tests;
    }

    public List<OperationType> getOperationTypes() {
        return operationTypes;
    }
}
