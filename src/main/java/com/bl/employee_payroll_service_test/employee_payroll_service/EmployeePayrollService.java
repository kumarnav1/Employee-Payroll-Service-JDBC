package com.bl.employee_payroll_service_test.employee_payroll_service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
    public List<EmployeePayrollData> employeePayrollList;

    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }

    public EmployeePayrollService() {
        employeePayrollList = new ArrayList<>();
    }

    public static void main(String[] args) {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        Scanner consoleInputReader = new Scanner(System.in);
        employeePayrollService.printWelcomeMessage();
        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollData(IOService.FILE_IO);
    }

    public void printWelcomeMessage() {
        System.out.println("Welcome To The Employee Payroll Service System");
    }

    private void readEmployeePayrollData(Scanner consoleInputReader) {
        System.out.println("Enter Employee ID : ");
        int id = consoleInputReader.nextInt();
        consoleInputReader.nextLine();
        System.out.println("Enter Employee Name : ");
        String name = consoleInputReader.nextLine();
        System.out.println("Enter Employee Salary : ");
        double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }

    public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService){
        if (ioService.equals(IOService.FILE_IO))
           this.employeePayrollList = new EmployeePayrollFileIOService().readData();
       if (ioService.equals(IOService.DB_IO))
            this.employeePayrollList = new employeePayrollDBService().readData();
        return employeePayrollList;
    }

    public void writeEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("\nWriting Employee Payroll Roaster to console\n" + employeePayrollList);
        else if (ioService.equals(IOService.FILE_IO)) {
            new EmployeePayrollFileIOService().writeData(employeePayrollList);
        }
    }

    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            return new EmployeePayrollFileIOService().countEntries();
        return 0;
    }

    public void printData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            new EmployeePayrollFileIOService().printData();
    }

    public enum IOService {CONSOLE_IO, FILE_IO, DB_IO}
}