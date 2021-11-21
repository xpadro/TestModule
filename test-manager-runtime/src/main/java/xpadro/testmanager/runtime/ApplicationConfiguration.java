package xpadro.testmanager.runtime;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import xpadro.testmanager.domain.operation.*;
import xpadro.testmanager.domain.operation.analysis.Analysis;
import xpadro.testmanager.domain.operation.analysis.BiochemistryAnalysis;
import xpadro.testmanager.domain.operation.calculation.BiochemistryCalculation;
import xpadro.testmanager.domain.operation.calculation.Calculation;
import xpadro.testmanager.domain.operation.calculation.ImmunologyCalculation;
import xpadro.testmanager.domain.port.OperationPort;
import xpadro.testmanager.domain.port.OperationResultPort;
import xpadro.testmanager.outbound.OperationResultRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "xpadro.testmanager")
public class ApplicationConfiguration {

    // Ports
    @Bean
    public OperationPort operationPort(@Qualifier("calculationOperation") Operation calculationOperation,
                                       @Qualifier("analysisOperation") Operation analysisOperation) {

        Map<OperationType, Operation> operations = new HashMap<>();
        operations.put(analysisOperation.getType(), analysisOperation);
        operations.put(calculationOperation.getType(), calculationOperation);

        return new OperationService(operations, operationResultPort());
    }

    @Bean
    public OperationResultPort operationResultPort() {
        return new OperationResultRepository();
    }

    // Operations
    @Bean
    public Operation calculationOperation(List<Calculation> calculations) {
        return new CalculationOperation(calculations);
    }

    @Bean
    public Operation analysisOperation(List<Analysis> analyses) {
        return new AnalysisOperation(analyses);
    }


    // Calculations
    @Bean
    public Calculation biochemistryCalculation() {
        return new BiochemistryCalculation();
    }

    @Bean
    public Calculation immunologyCalculation() {
        return new ImmunologyCalculation();
    }


    // Analysis
    @Bean
    public Analysis biochemistryAnalysis() {
        return new BiochemistryAnalysis();
    }

}
