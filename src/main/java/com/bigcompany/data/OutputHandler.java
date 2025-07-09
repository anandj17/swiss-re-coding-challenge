package com.bigcompany.data;

import com.bigcompany.model.Violation;

import java.util.List;

public interface OutputHandler {
    void handleViolations(List<Violation> violations);
}
