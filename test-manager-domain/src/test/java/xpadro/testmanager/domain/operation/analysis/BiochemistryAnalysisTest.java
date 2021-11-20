package xpadro.testmanager.domain.operation.analysis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xpadro.testmanager.domain.test.SampleTest;
import xpadro.testmanager.domain.test.impl.BiochemistryTest;
import xpadro.testmanager.domain.test.impl.ImmunologyTest;

import static org.assertj.core.api.Assertions.assertThat;

class BiochemistryAnalysisTest {
    private BiochemistryAnalysis analysis;

    @BeforeEach
    public void setUp() {
        analysis = new BiochemistryAnalysis();
    }

    @Test
    public void testSupportedType() {
        SampleTest test = new BiochemistryTest("test", 0.1f, 0.1f, 0.1f);
        assertThat(analysis.supports(test)).isTrue();
    }

    @Test
    public void testUnsupportedType() {
        SampleTest test = new ImmunologyTest("test");
        assertThat(analysis.supports(test)).isFalse();
    }

    @Test
    public void testAnalyseAssociatedSample() {
        SampleTest test = new BiochemistryTest("test", 0.1f, 0.2f, 0.1f);
        AnalysisResult result = (AnalysisResult) analysis.analyse(test);

        assertThat(result.isAssociated()).isTrue();
    }

    @Test
    public void testAnalyseNotAssociatedSample() {
        SampleTest test = new BiochemistryTest("test", 0.1f, 0.1f, 0.1f);
        AnalysisResult result = (AnalysisResult) analysis.analyse(test);

        assertThat(result.isAssociated()).isFalse();
    }
}