package com.bigcompany;

import com.bigcompany.data.CSVEmployeeDataSource;
import com.bigcompany.data.EmployeeDataSource;
import com.bigcompany.service.OrgAnalyzer;


public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: java -jar big-company-analysis.jar <employees.csv>");
            return;
        }

        String filePath = args[0];

        EmployeeDataSource dataSource = new CSVEmployeeDataSource(filePath);
        OrgAnalyzer analyzer = new OrgAnalyzer(dataSource);
        analyzer.analyzeAndReport();
    }
}
