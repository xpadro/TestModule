package xpadro.testmanager.domain.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xpadro.testmanager.domain.operation.analysis.AnalysisResult;
import xpadro.testmanager.domain.order.Order;
import xpadro.testmanager.domain.order.TestRequest;
import xpadro.testmanager.domain.test.SampleTest;
import xpadro.testmanager.domain.test.impl.ImmunologyTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationServiceTest {
    private OperationService operationService;

    @Mock
    private Operation operation;

    @BeforeEach
    public void setUp() {
        Map<OperationType, Operation> operations = new HashMap<>();
        operations.put(OperationType.ANALYSIS, operation);
        this.operationService = new OperationService(operations);
    }

    @Test
    public void testProcessOrder() {
        SampleTest sample = new ImmunologyTest("test");
        when(operation.operate(sample)).thenReturn(new AnalysisResult(true, true));
        TestRequest testRequest = new TestRequest(sample, Collections.singletonList(OperationType.ANALYSIS));
        List<TestRequest> testRequests = Collections.singletonList(testRequest);
        Order order = new Order(testRequests);

        List<OperationResult> results = operationService.processOrder(order);

        assertThat(results.size()).isEqualTo(1);
        assertThat(results.get(0).isProcessed()).isTrue();
    }

    @Test
    public void testProcessWithNoOrders() {
        Order order = new Order(Collections.emptyList());

        List<OperationResult> results = operationService.processOrder(order);

        assertThat(results.isEmpty()).isTrue();
    }

    @Test
    public void testThrowExceptionIfNoAvailableOperation() {
        SampleTest sample = new ImmunologyTest("test");
        TestRequest testRequest = new TestRequest(sample, Collections.singletonList(OperationType.CALCULATION));
        List<TestRequest> testRequests = Collections.singletonList(testRequest);
        Order order = new Order(testRequests);

        assertThatThrownBy(() -> operationService.processOrder(order)).isInstanceOf(IllegalStateException.class);
    }
}