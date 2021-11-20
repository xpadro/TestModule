package xpadro.testmanager.inbound.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xpadro.testmanager.domain.operation.OperationResult;
import xpadro.testmanager.domain.operation.OperationType;
import xpadro.testmanager.domain.order.Order;
import xpadro.testmanager.domain.order.TestRequest;
import xpadro.testmanager.domain.test.impl.BiochemistryTest;
import xpadro.testmanager.domain.test.impl.ImmunologyTest;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@RestController
public class TestManagerController {
    private final ProcessOrdersApiService processOrdersApiService;

    @Autowired
    public TestManagerController(ProcessOrdersApiService processOrdersApiService) {
        this.processOrdersApiService = processOrdersApiService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OperationResult>> processOrders() {
        List<OperationResult> results = processOrdersApiService.processOrder(buildSimulatedOrder());

        return ResponseEntity.ok(results);
    }

    private Order buildSimulatedOrder() {
        BiochemistryTest biochemistryTest = new BiochemistryTest("bio", 0.1f, 0.1f, 0.1f);
        ImmunologyTest immunologyTest = new ImmunologyTest("imm");

        List<TestRequest> testRequests = asList(
                new TestRequest(biochemistryTest, asList(OperationType.CALCULATION, OperationType.ANALYSIS)),
                new TestRequest(immunologyTest, singletonList(OperationType.CALCULATION)));

        return new Order(testRequests);
    }
}
