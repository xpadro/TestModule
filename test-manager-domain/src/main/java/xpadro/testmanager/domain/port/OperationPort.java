package xpadro.testmanager.domain.port;

import xpadro.testmanager.domain.operation.OperationResult;
import xpadro.testmanager.domain.order.Order;

import java.util.List;

public interface OperationPort {

    List<OperationResult> processOrder(Order order);
}
