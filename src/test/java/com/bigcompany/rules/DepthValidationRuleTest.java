package com.bigcompany.rules;

import com.bigcompany.data.MockEmployeeDataSource;
import com.bigcompany.data.MockEmployeeDataSourceDepthViolation;
import com.bigcompany.model.Employee;
import com.bigcompany.model.Violation;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DepthValidationRuleTest {

    @Test
    void testDepthViolationsDetection() {
        Map<String, Employee> employees = new MockEmployeeDataSourceDepthViolation().load();
        List<Violation> violations = new DepthValidationRule().evaluate(employees);

        assertFalse(violations.isEmpty(), "Expected depth violations but found none");
        assertTrue(
                violations.stream().anyMatch(v -> (int) v.getDetails().get("depth") > 4),
                "Expected at least one employee with depth > 4"
        );
    }

    @Test
    void testDepthViolationsNoViolation() {
        Map<String, Employee> employees = new MockEmployeeDataSource().load();
        List<Violation> violations = new DepthValidationRule().evaluate(employees);

        assertTrue(violations.isEmpty());
    }
}
