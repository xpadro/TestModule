package xpadro.testmanager.outbound;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xpadro.testmanager.domain.operation.OperationResult;

@Repository
public interface OperationResultRepository extends MongoRepository<OperationResult, Long> {
}
