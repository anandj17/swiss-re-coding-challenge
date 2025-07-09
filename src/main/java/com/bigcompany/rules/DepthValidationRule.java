package com.bigcompany.rules;

import com.bigcompany.model.DepthViolation;
import com.bigcompany.model.Employee;
import com.bigcompany.model.Violation;
import com.bigcompany.util.Constants;

import java.util.*;

public class DepthValidationRule implements ValidationRule {

    @Override
    public List<Violation> evaluate(Map<String, Employee> employees) {
        List<Violation> violations = new ArrayList<>();

        for (Employee e : employees.values()) {
            int depth = getDepth(e, employees);
            if (depth > Constants.MAX_HIERARCHY_DEPTH) {
                violations.add(new DepthViolation(e.getName(), depth));
            }
        }

        return violations;
    }

    private int getDepth(Employee e, Map<String, Employee> employees) {
        int depth = -1; // CEO should not be considered as Manager
        String managerId = e.getManagerId();

        while (managerId != null && employees.containsKey(managerId)) {
            depth++;
            managerId = employees.get(managerId).getManagerId();
        }

        return depth;
    }
}
