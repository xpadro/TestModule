package xpadro.testmanager.domain.operation;

import xpadro.testmanager.domain.test.SampleTest;

public interface Operation {

    OperationType getType();

    OperationResult operate(SampleTest test);
}
