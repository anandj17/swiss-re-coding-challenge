package com.bigcompany.data;

import com.bigcompany.model.Employee;

import java.util.HashMap;
import java.util.Map;

public class MockEmployeeDataSource implements EmployeeDataSource {

    @Override
    public Map<String, Employee> load() {
        Map<String, Employee> employees = new HashMap<>();

        employees.put("E1", new Employee("E1", "CEO", "CEO", 10000, null));
        employees.put("E2", new Employee("E2", "Manager1", "Level1", 2300, "E1"));
        employees.put("E3", new Employee("E3", "Manager2", "Level1", 2000, "E1"));
        employees.put("E4", new Employee("E4", "Manager3", "Level2", 2000, "E2"));
        employees.put("E5", new Employee("E5", "Manager4", "Level3", 10000, "E4"));
        employees.put("E6", new Employee("E6", "Manager5", "Level4", 800, "E5"));
        employees.put("E7", new Employee("E7", "Manager6", "Level5", 700, "E6"));
        employees.put("E8", new Employee("E8", "NO", "Subordinate", 700, "E6"));
        return employees;
    }
}
