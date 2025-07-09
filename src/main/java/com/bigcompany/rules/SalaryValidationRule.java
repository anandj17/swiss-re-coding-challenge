package com.bigcompany.rules;

import com.bigcompany.model.Employee;
import com.bigcompany.model.SalaryViolation;
import com.bigcompany.model.Violation;
import com.bigcompany.util.Constants;

import java.util.*;

public class SalaryValidationRule implements ValidationRule {

    @Override
    public List<Violation> evaluate(Map<String, Employee> employees) {
        List<Violation> violations = new ArrayList<>();

        for (Employee manager : employees.values()) {
            List<Employee> subordinates = getDirectSubordinates(manager.getId(), employees);

            if (subordinates.isEmpty() || manager.getManagerId() == null) continue;

            double avgSalary = subordinates.stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0.0);

            double minSalary = avgSalary * Constants.SALARY_MIN_MULTIPLIER;
            double maxSalary = avgSalary * Constants.SALARY_MAX_MULTIPLIER;

            if( manager.getSalary() >= minSalary && manager.getSalary() <= maxSalary) {
                continue; // No violation
            }

            SalaryViolation.Type violationType = SalaryViolation.Type.TOO_LOW;
            if (manager.getSalary() > maxSalary) {
                violationType = SalaryViolation.Type.TOO_HIGH;
            }

            violations.add(new SalaryViolation(
                    manager.getName(),
                    manager.getSalary(),
                    minSalary,
                    maxSalary,
                    violationType,
                    manager.getSalary() - maxSalary
            ));
        }

        return violations;
    }

    private List<Employee> getDirectSubordinates(String managerId, Map<String, Employee> employees) {
        List<Employee> subs = new ArrayList<>();
        for (Employee e : employees.values()) {
            if (managerId.equals(e.getManagerId())) {
                subs.add(e);
            }
        }
        return subs;
    }
}
