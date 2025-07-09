package com.bigcompany.service;

import com.bigcompany.data.ConsoleOutputHandler;
import com.bigcompany.data.OutputHandler;
import com.bigcompany.model.Employee;
import com.bigcompany.model.Violation;
import com.bigcompany.rules.DepthValidationRule;
import com.bigcompany.rules.SalaryValidationRule;
import com.bigcompany.rules.ValidationRule;
import com.bigcompany.data.EmployeeDataSource;

import java.util.*;

public class OrgAnalyzer {

    private final EmployeeDataSource dataSource;
    private final List<ValidationRule> rules;

    public OrgAnalyzer(EmployeeDataSource dataSource) {
        this.dataSource = dataSource;
        this.rules = Arrays.asList(
                new SalaryValidationRule(),
                new DepthValidationRule()
        );
    }

    public void analyzeAndReport() throws Exception {
        Map<String, Employee> employees = dataSource.load();

        OutputHandler outputHandler = new ConsoleOutputHandler();
        for (ValidationRule rule : rules) {
            List<Violation> violations = rule.evaluate(employees);
            outputHandler.handleViolations(violations);
        }
    }
}
