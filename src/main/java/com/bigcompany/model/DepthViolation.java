package com.bigcompany.model;

import com.bigcompany.util.Constants;

import java.util.HashMap;
import java.util.Map;

public class DepthViolation implements Violation {
    private final String employeeName;
    private final int depth;

    public DepthViolation(String employeeName, int depth) {
        this.employeeName = employeeName;
        this.depth = depth;
    }

    private int getDepth() {
        return depth;
    }

    @Override
    public String getEmployeeName() {
        return employeeName;
    }

    @Override
    public String getMessage() {
        return String.format("Depth violation: %s has reporting line of %d, which is more by %d (limit: 4)%n", employeeName, depth, depth - Constants.MAX_HIERARCHY_DEPTH);
    }

    @Override
    public Map<String, Object> getDetails() {
        Map<String, Object> map = new HashMap<>();
        map.put("depth", getDepth());
        return map;
    }
}
