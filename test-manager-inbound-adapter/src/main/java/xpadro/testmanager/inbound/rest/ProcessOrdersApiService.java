package xpadro.testmanager.inbound.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import xpadro.testmanager.domain.operation.Operation;
import xpadro.testmanager.domain.operation.OperationResult;
import xpadro.testmanager.domain.order.Order;
import xpadro.testmanager.domain.order.TestRequest;
import xpadro.testmanager.domain.test.impl.BiochemistryTest;
import xpadro.testmanager.domain.test.impl.ImmunologyTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessOrdersApiService {
    private final Operation calculationOperation;
    private final Operation analysisOperation;

    @Autowired
    public ProcessOrdersApiService(@Qualifier("calculationOperation") Operation calculationOperation,
                                   @Qualifier("analysisOperation") Operation analysisOperation) {

        this.calculationOperation = calculationOperation;
        this.analysisOperation = analysisOperation;
    }

    public List<OperationResult> processOrders() {
        Order order = buildOrder();

        return order.getTestRequests().stream()
                .flatMap(request -> performRequest(request).stream())
                .collect(Collectors.toList());
    }

    private Order buildOrder() {
        BiochemistryTest biochemistryTest = new BiochemistryTest("bio", 0.1f, 0.1f, 0.1f);
        ImmunologyTest immunologyTest = new ImmunologyTest("imm");

        List<TestRequest> testRequests = Arrays.asList(
                new TestRequest(biochemistryTest, Arrays.asList(calculationOperation, analysisOperation)),
                new TestRequest(immunologyTest, Collections.singletonList(calculationOperation)));

        return new Order(testRequests);
    }

    private List<OperationResult> performRequest(TestRequest testRequest) {
        return testRequest.getOperations().stream()
                .map(op -> runOperation(testRequest, op))
                .collect(Collectors.toList());
    }

    private OperationResult runOperation(TestRequest testRequest, Operation operation) {
        return operation.operate(testRequest.getTest());
    }
}
