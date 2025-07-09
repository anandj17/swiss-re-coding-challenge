package com.bigcompany.rules;

import com.bigcompany.model.Employee;
import com.bigcompany.model.Violation;

import java.util.List;
import java.util.Map;

public interface ValidationRule {
    List<Violation> evaluate(Map<String, Employee> employees);
}
