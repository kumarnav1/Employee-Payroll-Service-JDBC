package com.bl.employee_payroll_service_test.employee_payroll_service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollFileIOService {
    public static String PAYROLL_FILE_NAME = "src/main/resources/payroll-file-printed.txt";

    public void writeData(List<EmployeePayrollData> employeePayrollList) {
        StringBuffer empBuffer = new StringBuffer();
        employeePayrollList.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });

        try {
            Files.write(Paths.get(PAYROLL_FILE_NAME), empBuffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long countEntries() {
        long entries = 0;
        try {
            entries = Files.lines(new File("src/main/resources/payroll-file.txt").toPath()).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public void printData() {
        try {
            Files.lines(new File("src/main/resources/payroll-file-printed.txt").toPath()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<EmployeePayrollData> readData() {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try {
            Files.lines(new File("src/main/resources/payroll-file.txt").toPath())
                    .map(String::trim).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }
}