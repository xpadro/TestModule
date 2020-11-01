package xpadro.testmodule.businesslogic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xpadro.testmodule.businesslogic.operation.OperationResult;
import xpadro.testmodule.businesslogic.operation.OperationType;
import xpadro.testmodule.businesslogic.operation.analysis.AnalysisResult;
import xpadro.testmodule.businesslogic.operation.calculation.CalculationResult;
import xpadro.testmodule.businesslogic.order.Order;
import xpadro.testmodule.businesslogic.port.PerformTestsPort;
import xpadro.testmodule.businesslogic.test.BiochemistryTest;
import xpadro.testmodule.businesslogic.test.ImmunologyTest;
import xpadro.testmodule.businesslogic.test.SampleTest;
import xpadro.testmodule.serverside.QueryResultsPort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ComponentTest {
    @Autowired
    private PerformTestsPort performTestsPort;

    @Autowired
    private QueryResultsPort queryResultsPort;

    @Test
    public void successfulBatteryOfTests() {
        SampleTest test1 = new BiochemistryTest("glucose", 0.7f, 0.6f, 0.6f);
        SampleTest test2 = new BiochemistryTest("sodium", 0.4f, 0.6f, 0.2f);
        List<SampleTest> tests = Arrays.asList(test1, test2);
        List<OperationType> operationTypes = Arrays.asList(OperationType.CALCULATION, OperationType.ANALYSIS);
        Order order = new Order(tests, operationTypes);
        performTestsPort.execute(order);

        List<OperationResult> results = queryResultsPort.query();

        assertThat(results.size(), equalTo(4));

        CalculationResult test1Calculation = (CalculationResult) results.get(0);
        assertFalse(test1Calculation.isBelowThreshold());

        CalculationResult test2Calculation = (CalculationResult) results.get(1);
        assertTrue(test2Calculation.isBelowThreshold());

        AnalysisResult test1Analysis = (AnalysisResult) results.get(2);
        assertFalse(test1Analysis.isAssociated());

        AnalysisResult test2Analysis = (AnalysisResult) results.get(3);
        assertTrue(test2Analysis.isAssociated());
    }

    @Test
    public void batteryFailsDueToNoSuitableOperationForImmunologyTest() {
        SampleTest test = new ImmunologyTest("test");
        List<OperationType> operationTypes = Arrays.asList(OperationType.CALCULATION, OperationType.ANALYSIS);
        Order order = new Order(Collections.singletonList(test), operationTypes);
        assertThrows(IllegalStateException.class, () ->performTestsPort.execute(order));
    }
}
