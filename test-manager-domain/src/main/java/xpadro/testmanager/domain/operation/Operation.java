package xpadro.testmanager.domain.operation;

import xpadro.testmanager.domain.test.SampleTest;

public interface Operation {

    OperationResult operate(SampleTest test);
}
