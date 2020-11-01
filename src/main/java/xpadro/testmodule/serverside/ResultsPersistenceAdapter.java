package xpadro.testmodule.serverside;

import org.springframework.stereotype.Service;
import xpadro.testmodule.businesslogic.operation.OperationResult;
import xpadro.testmodule.businesslogic.port.HandleResultsPort;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultsPersistenceAdapter implements HandleResultsPort, QueryResultsPort {
    /**
     * For the purpose of this test, this class is just a mock in-memory database
     * implemented with a List
     */
    private List<OperationResult> results = new ArrayList<>();

    @Override
    public void handle(List<OperationResult> results) {
        this.results.addAll(results);
    }

    @Override
    public List<OperationResult> query() {
        return this.results;
    }
}
