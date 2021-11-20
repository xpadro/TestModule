package xpadro.testmanager.inbound.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xpadro.testmanager.domain.operation.OperationResult;

import java.util.Collections;
import java.util.List;

@RestController
public class TestManagerController {

    @GetMapping
    public ResponseEntity<List<OperationResult>> processOrder() {
        return ResponseEntity.ok(Collections.emptyList());
    }
}
