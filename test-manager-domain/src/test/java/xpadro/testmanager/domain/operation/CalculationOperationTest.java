package xpadro.testmanager.domain.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xpadro.testmanager.domain.operation.calculation.Calculation;
import xpadro.testmanager.domain.operation.calculation.CalculationResult;
import xpadro.testmanager.domain.test.SampleTest;
import xpadro.testmanager.domain.test.impl.ImmunologyTest;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculationOperationTest {
    private CalculationOperation operation;

    @Mock
    private Calculation calculation;

    @BeforeEach
    public void setUp() {
        operation = new CalculationOperation(singletonList(calculation));
    }

    @Test
    public void testExpectedType() {
        assertThat(operation.getType()).isEqualTo(OperationType.CALCULATION);
    }

    @Test
    public void testOperateTest() {
        SampleTest test = new ImmunologyTest("test");
        when(calculation.supports(test)).thenReturn(true);
        CalculationResult expectedResult = new CalculationResult(true, true);
        when(calculation.calculate(test)).thenReturn(expectedResult);

        OperationResult result = operation.operate(test);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testOperateTestWithNoSupportedOperation() {
        SampleTest test = new ImmunologyTest("test");
        when(calculation.supports(test)).thenReturn(false);

        assertThatThrownBy(() -> operation.operate(test)).isInstanceOf(IllegalStateException.class);
    }

}