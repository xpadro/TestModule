package xpadro.testmanager.domain.operation.calculation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xpadro.testmanager.domain.test.SampleTest;
import xpadro.testmanager.domain.test.impl.BiochemistryTest;
import xpadro.testmanager.domain.test.impl.ImmunologyTest;

import static org.assertj.core.api.Assertions.assertThat;

class BiochemistryCalculationTest {
    private BiochemistryCalculation calculation;

    @BeforeEach
    public void setUp() {
        calculation = new BiochemistryCalculation();
    }

    @Test
    public void testSupportedType() {
        SampleTest test = new BiochemistryTest("test", 0.1f, 0.1f, 0.1f);
        assertThat(calculation.supports(test)).isTrue();
    }

    @Test
    public void testUnsupportedType() {
        SampleTest test = new ImmunologyTest("test");
        assertThat(calculation.supports(test)).isFalse();
    }

    @Test
    public void testAnalyseBelowThreshold() {
        SampleTest test = new BiochemistryTest("test", 0.1f, 0.2f, 0.1f);
        CalculationResult result = (CalculationResult) calculation.calculate(test);

        assertThat(result.isBelowThreshold()).isTrue();
    }

    @Test
    public void testAnalyseNotAssociatedSample() {
        SampleTest test = new BiochemistryTest("test", 0.8f, 0.1f, 0.1f);
        CalculationResult result = (CalculationResult) calculation.calculate(test);

        assertThat(result.isBelowThreshold()).isFalse();
    }

}