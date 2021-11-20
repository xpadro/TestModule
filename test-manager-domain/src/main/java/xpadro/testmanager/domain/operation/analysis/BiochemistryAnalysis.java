package xpadro.testmanager.domain.operation.analysis;

import xpadro.testmanager.domain.operation.OperationResult;
import xpadro.testmanager.domain.test.SampleTest;
import xpadro.testmanager.domain.test.impl.BiochemistryTest;

public class BiochemistryAnalysis implements Analysis {

    @Override
    public boolean supports(SampleTest test) {
        return test.getClass().isAssignableFrom(BiochemistryTest.class);
    }

    @Override
    public OperationResult analyse(SampleTest test) {
        System.out.println("Biochemistry analysis on " + test.getName());

        BiochemistryTest bioTest = (BiochemistryTest) test;
        boolean isAssociated = bioTest.getHbA1cLevelInMoPeriod() > bioTest.getHbA1cLevelInPeacePeriod();

        return new AnalysisResult(true, isAssociated);
    }
}
