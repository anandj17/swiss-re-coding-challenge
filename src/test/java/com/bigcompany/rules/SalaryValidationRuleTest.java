package com.bigcompany.rules;

import com.bigcompany.data.MockEmployeeDataSource;
import com.bigcompany.data.MockEmployeeDataSourceNoViolation;
import com.bigcompany.data.MockEmployeeDataSourceSalaryViolationHigh;
import com.bigcompany.data.MockEmployeeDataSourceSalaryViolationLow;
import com.bigcompany.model.Employee;
import com.bigcompany.model.SalaryViolation;
import com.bigcompany.model.Violation;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SalaryValidationRuleTest {

    @Test
    void testSalaryComplianceNoViolation() throws Exception {
        Map<String, Employee> employees = new MockEmployeeDataSourceNoViolation().load();
        List<Violation> violations = new SalaryValidationRule().evaluate(employees);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testSalaryComplianceDetectionLow() throws Exception {
        Map<String, Employee> employees = new MockEmployeeDataSourceSalaryViolationLow().load();
        List<Violation> violations = new SalaryValidationRule().evaluate(employees);

        assertFalse(violations.isEmpty(), "Expected salary violations but found none");
        assertTrue(
                violations.stream().anyMatch(v -> v.getDetails().get("type") == SalaryViolation.Type.TOO_LOW),
                "Expected at least one manager earning too little"
        );
    }

    @Test
    void testSalaryComplianceDetectionHigh() {
        Map<String, Employee> employees = new MockEmployeeDataSourceSalaryViolationHigh().load();
        List<Violation> violations = new SalaryValidationRule().evaluate(employees);

        assertFalse(violations.isEmpty(), "Expected salary violations but found none");
        assertTrue(
                violations.stream().anyMatch(v -> v.getDetails().get("type") == SalaryViolation.Type.TOO_HIGH),
                "Expected at least one manager earning too more"
        );
    }

}
