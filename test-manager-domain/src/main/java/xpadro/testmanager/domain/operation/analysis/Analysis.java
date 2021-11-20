package xpadro.testmanager.domain.operation.analysis;

import xpadro.testmanager.domain.operation.OperationResult;
import xpadro.testmanager.domain.test.SampleTest;

public interface Analysis {

    boolean supports(SampleTest test);

    OperationResult analyse(SampleTest test);
}
