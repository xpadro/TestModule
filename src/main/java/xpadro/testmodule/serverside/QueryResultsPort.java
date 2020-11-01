package xpadro.testmodule.serverside;

import xpadro.testmodule.businesslogic.operation.OperationResult;

import java.util.List;

public interface QueryResultsPort {

    List<OperationResult> query();
}
