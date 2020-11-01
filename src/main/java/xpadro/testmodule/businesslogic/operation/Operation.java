package xpadro.testmodule.businesslogic.operation;

import xpadro.testmodule.businesslogic.test.SampleTest;

import java.util.List;

public interface Operation {

    boolean supports(OperationType type);

    List<OperationResult> operate(List<SampleTest> tests);
}
