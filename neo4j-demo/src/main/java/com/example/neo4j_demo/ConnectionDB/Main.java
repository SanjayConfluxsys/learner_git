package com.example.neo4j_demo.ConnectionDB;

import service.EmployeeService;

public class Main {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();

        // Create a new employee
      // service.createEmployee("Emma", "Junior Engineer","21999");
//
//        // Retrieve employee
//        service.getEmployee("john");

        service.getAllEmployee();

        //service.relationship("Emma","Engineering");
//
//        // Update employee role
        //service.updateEmployeeRole("Satish", "Senior Developer");
//
//        // Delete employee
       // service.deleteEmployee("scope");
        //  service.createDepartment("Engineering");


        dbConnection.closeConnection();
    }
}
