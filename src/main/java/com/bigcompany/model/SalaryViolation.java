package com.bigcompany.model;

import java.util.HashMap;
import java.util.Map;

public class SalaryViolation implements Violation {
    public enum Type {TOO_LOW, TOO_HIGH}

    private final String managerName;
    private final double actualSalary;
    private final double expectedMin;
    private final double expectedMax;
    private final Type type;
    private final double difference;

    public SalaryViolation(String managerName, double actualSalary, double expectedMin, double expectedMax, Type type, double difference) {
        this.managerName = managerName;
        this.actualSalary = actualSalary;
        this.expectedMin = expectedMin;
        this.expectedMax = expectedMax;
        this.type = type;
        this.difference = difference;
    }

    private double getActualSalary() {
        return actualSalary;
    }

    private Type getType() {
        return type;
    }

    private double getDifference() {
        return difference;
    }

    @Override
    public String getEmployeeName() {
        return managerName;
    }

    @Override
    public String getMessage() {
        return String.format("Salary violation: %s earns %.2f (should be between %.2f and %.2f). Violation type: %s (%.2f)%n",
                managerName, actualSalary, expectedMin, expectedMax, type, difference);
    }

    @Override
    public Map<String, Object> getDetails() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", getType());
        return map;
    }
}
