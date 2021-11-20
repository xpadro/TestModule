package xpadro.testmanager.inbound;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xpadro.testmanager.domain.operation.AnalysisOperation;
import xpadro.testmanager.domain.operation.CalculationOperation;
import xpadro.testmanager.domain.operation.Operation;
import xpadro.testmanager.domain.operation.analysis.Analysis;
import xpadro.testmanager.domain.operation.analysis.BiochemistryAnalysis;
import xpadro.testmanager.domain.operation.calculation.BiochemistryCalculation;
import xpadro.testmanager.domain.operation.calculation.Calculation;
import xpadro.testmanager.domain.operation.calculation.ImmunologyCalculation;

import java.util.List;

@Configuration
public class ApplicationConfiguration {

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
