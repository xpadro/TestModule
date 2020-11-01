package xpadro.testmodule.businesslogic.operation.analysis;

import org.springframework.stereotype.Component;
import xpadro.testmodule.businesslogic.operation.OperationHandler;
import xpadro.testmodule.businesslogic.operation.OperationType;
import xpadro.testmodule.businesslogic.test.BiochemistryTest;
import xpadro.testmodule.businesslogic.test.SampleTest;

@Component
public class BiochemistryAnalysisHandler implements OperationHandler {

    @Override
    public boolean supports(OperationType type) {
        return OperationType.ANALYSIS.equals(type);
    }

    @Override
    public boolean canHandle(SampleTest test) {
        return test.getClass().isAssignableFrom(BiochemistryTest.class);
    }

    @Override
    public AnalysisResult handle(SampleTest test) {
        BiochemistryTest bioTest = (BiochemistryTest) test;
        boolean isAssociated = bioTest.getHbA1cLevelInMoPeriod() > bioTest.getHbA1cLevelInPeacePeriod();

        return new AnalysisResult(true, isAssociated);
    }
}
