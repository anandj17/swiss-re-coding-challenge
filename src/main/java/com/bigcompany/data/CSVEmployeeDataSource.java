package com.bigcompany.data;

import com.bigcompany.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVEmployeeDataSource implements EmployeeDataSource {

    private final String filePath;

    public CSVEmployeeDataSource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Map<String, Employee> load() {
        Map<String, Employee> employeeMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            String[] headers = line.split(",", -1);
            Map<String, Integer> headerIndexMap = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                headerIndexMap.put(headers[i].trim().toLowerCase(), i);
            }

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] tokens = line.split(",", -1); // -1 includes empty values

                String id = tokens[headerIndexMap.get("id")].trim();
                String firstName = tokens[headerIndexMap.get("firstname")].trim();
                String lastName = tokens[headerIndexMap.get("lastname")].trim();
                double salary = Double.parseDouble(tokens[headerIndexMap.get("salary")].trim());
                String managerId = tokens[headerIndexMap.get("managerid")].trim().isEmpty() ? null : tokens[headerIndexMap.get("managerid")].trim();

                Employee employee = new Employee(id, firstName, lastName, salary, managerId);
                employeeMap.put(id, employee);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file", e);
        }

        return employeeMap;
    }
}
