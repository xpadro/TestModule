package xpadro.testmanager.inbound.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xpadro.testmanager.domain.operation.OperationResult;
import xpadro.testmanager.domain.order.Order;
import xpadro.testmanager.domain.port.OperationPort;

import java.util.List;

@Service
public class ProcessOrdersApiService {
    private final OperationPort operationPort;

    @Autowired
    public ProcessOrdersApiService(OperationPort operationPort) {
        this.operationPort = operationPort;
    }

    public List<OperationResult> processOrder(Order order) {
        return operationPort.processOrder(order);
    }
}
