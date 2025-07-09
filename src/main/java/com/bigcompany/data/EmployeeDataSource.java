package com.bigcompany.data;

import com.bigcompany.model.Employee;

import java.util.Map;

public interface EmployeeDataSource {
    Map<String, Employee> load() throws Exception;
}
