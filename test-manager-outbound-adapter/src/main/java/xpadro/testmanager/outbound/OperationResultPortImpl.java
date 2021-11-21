package xpadro.testmanager.outbound;

import org.springframework.beans.factory.annotation.Autowired;
import xpadro.testmanager.domain.operation.OperationResult;
import xpadro.testmanager.domain.port.OperationResultPort;

public class OperationResultPortImpl implements OperationResultPort {
    private final OperationResultRepository operationResultRepository;

    @Autowired
    public OperationResultPortImpl(OperationResultRepository operationResultRepository) {
        this.operationResultRepository = operationResultRepository;
    }

    @Override
    public void persist(OperationResult result) {
        operationResultRepository.save(result);
    }
}
