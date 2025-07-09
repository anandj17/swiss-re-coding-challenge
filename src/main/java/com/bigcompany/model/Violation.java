package com.bigcompany.model;

import java.util.Map;

public interface Violation {
    String getEmployeeName();

    String getMessage();

    Map<String, Object> getDetails();
}
