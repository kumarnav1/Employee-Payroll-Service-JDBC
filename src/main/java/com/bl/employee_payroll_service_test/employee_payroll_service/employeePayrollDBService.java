package com.bl.employee_payroll_service_test.employee_payroll_service;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class employeePayrollDBService {

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/employee_payroll_service?useSSL=false";
        String userName = "root";
        String password = "jkl;";
        Connection con;
        System.out.println("Connecting to database: " + jdbcURL);
        con = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection is successful!!!!!!" + con);
        return con;
    }

    public List<EmployeePayrollData> readData() {
        String sql = "SELECT * FROM employee_payroll; ";
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate startDate = resultSet.getDate("start").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id, name, salary, startDate));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return employeePayrollList;
    }
}