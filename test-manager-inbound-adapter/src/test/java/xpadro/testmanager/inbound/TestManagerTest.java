package xpadro.testmanager.inbound;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xpadro.testmanager.domain.operation.AnalysisOperation;
import xpadro.testmanager.domain.operation.CalculationOperation;
import xpadro.testmanager.domain.operation.OperationResult;
import xpadro.testmanager.domain.order.Order;
import xpadro.testmanager.domain.order.TestRequest;
import xpadro.testmanager.domain.test.impl.BiochemistryTest;
import xpadro.testmanager.domain.test.impl.ImmunologyTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class TestManagerTest {

    @Autowired
    private CalculationOperation calculationOperation;

    @Autowired
    private AnalysisOperation analysisOperation;

    @Test
    public void testRequests() {
        BiochemistryTest biochemistryTest = new BiochemistryTest("bio", 0.1f, 0.1f, 0.1f);
        ImmunologyTest immunologyTest = new ImmunologyTest("imm");

        List<TestRequest> testRequests = Arrays.asList(
                new TestRequest(biochemistryTest, Arrays.asList(calculationOperation, analysisOperation)),
                new TestRequest(immunologyTest, Collections.singletonList(calculationOperation)));

        Order order = new Order(testRequests);
        order.getTestRequests().forEach(testRequest -> performRequest(testRequest));

        assertThat(order.getTestRequests().size(), equalTo(2));
    }

    private void performRequest(TestRequest testRequest) {
        testRequest.getOperations().forEach(op -> operate(testRequest, op));
    }

    private OperationResult operate(TestRequest testRequest, xpadro.testmanager.domain.operation.Operation op) {
        return op.operate(testRequest.getTest());
    }
}
