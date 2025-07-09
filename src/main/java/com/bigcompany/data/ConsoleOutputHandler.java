package com.bigcompany.data;

import com.bigcompany.model.Violation;

import java.util.List;

public class ConsoleOutputHandler implements OutputHandler {

    @Override
    public void handleViolations(List<Violation> violations) {
        for (Violation violation : violations) {
            System.out.printf(violation.getMessage());
        }
    }
}
