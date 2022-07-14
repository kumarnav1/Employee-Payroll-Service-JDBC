package com.bl.employee_payroll_service_test;

import com.bl.employee_payroll_service_test.employee_payroll_service.EmployeePayrollData;
import com.bl.employee_payroll_service_test.employee_payroll_service.EmployeePayrollService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.bl.employee_payroll_service_test.employee_payroll_service.EmployeePayrollService.IOService.DB_IO;

public class EmployeePayrollServiceTest {
    @Test
    void given3Employees_WhenWrittenToFile_ShouldMatchEmployeeEntries() {
        EmployeePayrollData[] arrayOfEmps = {
                new EmployeePayrollData(101, "Jeff Bezos", 1000000.0),
                new EmployeePayrollData(101, "Bill Gates", 2000000.0),
                new EmployeePayrollData(101, "Mark Zuckerberg", 3000000.0),
        };
        EmployeePayrollService employeePayrollService;
        employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmps));
        employeePayrollService.writeEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
        employeePayrollService.printData(EmployeePayrollService.IOService.FILE_IO);
        long entries = employeePayrollService.countEntries(EmployeePayrollService.IOService.FILE_IO);
        Assertions.assertEquals(3, entries);
    }

  @Test
    void givenFileOnReadingFromFileShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
        long entries = employeePayrollService.countEntries(EmployeePayrollService.IOService.FILE_IO);
        Assertions.assertEquals(3, entries);
    }

    @Test
    void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DB_IO);
        Assertions.assertEquals(3, employeePayrollData.size());
    }
}