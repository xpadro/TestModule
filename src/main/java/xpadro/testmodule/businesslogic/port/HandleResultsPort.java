package xpadro.testmodule.businesslogic.port;

import xpadro.testmodule.businesslogic.operation.OperationResult;

import java.util.List;

public interface HandleResultsPort {

    void handle(List<OperationResult> results);
}
