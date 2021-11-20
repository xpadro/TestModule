package xpadro.testmanager.domain.port;

import xpadro.testmanager.domain.operation.OperationResult;

public interface OperationResultPort {

    void persist(OperationResult result);
}
