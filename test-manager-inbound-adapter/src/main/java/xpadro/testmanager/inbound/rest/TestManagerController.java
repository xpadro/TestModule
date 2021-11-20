package xpadro.testmanager.inbound.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xpadro.testmanager.domain.operation.OperationResult;

import java.util.List;

@RestController
public class TestManagerController {
    private final ProcessOrdersApiService processOrdersApiService;

    @Autowired
    public TestManagerController(ProcessOrdersApiService processOrdersApiService) {
        this.processOrdersApiService = processOrdersApiService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OperationResult>> processOrders() {
        List<OperationResult> results = processOrdersApiService.processOrders();

        return ResponseEntity.ok(results);
    }

}
