package xpadro.testmanager.domain.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xpadro.testmanager.domain.operation.analysis.Analysis;
import xpadro.testmanager.domain.operation.analysis.AnalysisResult;
import xpadro.testmanager.domain.test.SampleTest;
import xpadro.testmanager.domain.test.impl.ImmunologyTest;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnalysisOperationTest {
    private AnalysisOperation operation;

    @Mock
    private Analysis analysis;

    @BeforeEach
    public void setUp() {
        operation = new AnalysisOperation(singletonList(analysis));
    }

    @Test
    public void testExpectedType() {
        assertThat(operation.getType()).isEqualTo(OperationType.ANALYSIS);
    }

    @Test
    public void testOperateTest() {
        SampleTest test = new ImmunologyTest("test");
        when(analysis.supports(test)).thenReturn(true);
        AnalysisResult expectedResult = new AnalysisResult(true, true);
        when(analysis.analyse(test)).thenReturn(expectedResult);

        OperationResult result = operation.operate(test);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testOperateTestWithNoSupportedOperation() {
        SampleTest test = new ImmunologyTest("test");
        when(analysis.supports(test)).thenReturn(false);

        assertThatThrownBy(() -> operation.operate(test)).isInstanceOf(IllegalStateException.class);
    }

}