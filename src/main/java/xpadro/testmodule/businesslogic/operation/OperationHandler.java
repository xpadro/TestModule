package xpadro.testmodule.businesslogic.operation;

import xpadro.testmodule.businesslogic.test.SampleTest;

public interface OperationHandler {

    boolean supports(OperationType type);

    boolean canHandle(SampleTest test);

    OperationResult handle(SampleTest test);
}
