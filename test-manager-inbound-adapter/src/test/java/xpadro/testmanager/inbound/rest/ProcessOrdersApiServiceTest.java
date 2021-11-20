package xpadro.testmanager.inbound.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xpadro.testmanager.domain.operation.OperationResult;
import xpadro.testmanager.domain.operation.calculation.CalculationResult;
import xpadro.testmanager.domain.order.Order;
import xpadro.testmanager.domain.port.OperationPort;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessOrdersApiServiceTest {
    @InjectMocks
    private ProcessOrdersApiService service;

    @Mock
    private OperationPort operationPort;

    @BeforeEach
    public void setUp() {
        service = new ProcessOrdersApiService(operationPort);
    }

    @Test
    public void domainIsInvokedWithRequestedOrder() {
        Order order = new Order(Collections.emptyList());
        OperationResult expectedResult = new CalculationResult(true, false);
        List<OperationResult> operationResults = singletonList(expectedResult);
        when(operationPort.processOrder(order)).thenReturn(operationResults);

        List<OperationResult> results = service.processOrder(order);

        assertThat(results.size()).isEqualTo(1);
        assertThat(results.get(0)).isEqualTo(expectedResult);
    }

}