package service;
//package com.example.neo4j;

import com.example.neo4j_demo.ConnectionDB.dbConnection;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.exceptions.Neo4jException;
import org.neo4j.driver.types.Relationship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.neo4j.driver.Values.ofOffsetDateTime;
import static org.neo4j.driver.Values.parameters;

public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private static Driver driver = dbConnection.createDriver();

    // Create a new employee node
    public void createEmployee(String name, String role, String salary) {
        String query = "use sanjay CREATE (e:employee {name: $name, role: $role, salary: $salary}) RETURN e";

        try (Session session = driver.session()) {
            session.run(query, parameters("name", name, "role", role, "salary", salary));
            logger.info("Employee created: " + name);
        } catch (Neo4jException e) {
            logger.error("Error creating employee: " + e.getMessage());
            //e.printStackTrace();
        }
    }

    // Read employee information by name
    public void getEmployee(String name) {
        //String query = "use sanjay MATCH (e:employee {name: $name}) RETURN e";
        //String query = "use sanjay match(e:employee) return e";
        try (Session session = driver.session()) {
            //Result result = session.run(query, parameters("name", name));
            Result result = session.run("use sanjay match(e:employee {name:$name}) return e",parameters("name",name));
            if (result.hasNext()) {
                logger.info("Employee found: " + result.next().get("e").asNode().toString());
            } else {
                logger.error("Employee not found: " + name);
            }
        } catch (Exception e) {
            logger.error("Error retrieving employee: " + e.getMessage());
            //e.printStackTrace();
        }
    }

    public void getAllEmployee() {
        try(Session session = driver.session()){
            Result result = session.run("use sanjay match (e:employee) return e");
            if (result.hasNext()){
                while (result.hasNext()){
                    var record  = result.next();
                    var employeeNode = record.get("e").asNode();
                    logger.info("Employees: "+employeeNode.asMap());
                }
            }
            else {
                logger.error("Employees not found");
            }} catch (Neo4jException e) {
            logger.error("Failed to retrieve employees");
            //e.printStackTrace();
        }
    }
    public void relationship(String eName, String dName){
        try (Session session = driver.session()){
            Result result=session.run("use sanjay match(e:employee{name:$eName}),(d:department{name:$dName}) create (e)-[w:worksfor]->(d) return e,w,d"
            ,parameters("eName",eName,"dName",dName));
            if (result.hasNext()) {
                while (result.hasNext()) {
                    var record = result.next();
                    var employeeNode = record.get("e").asNode();
                    Relationship relationship = record.get("w").asRelationship();
                    var departmentNode = record.get("d").asNode();
                    logger.info("Employee name: " + employeeNode.asMap());
                    logger.info("Department name: " + departmentNode.asMap());
                    logger.info("Relationship: " + relationship.type());
                }
//                logger.info("Employee name: " + record.get("e").asNode().toString());
//                logger.info("Relationship: "+record.get("w").asRelationship().toString());
//                logger.info("Department Name: "+record.get("d").asNode().toString());
            }else {
                logger.error("relationship not created");

            }
        }catch (Neo4jException e){
            logger.error("Relationship not established");
            //e.printStackTrace();
        }

    }

    // Update an employee's role
    public void updateEmployeeRole(String name, String newRole) {
        String query = "use sanjay match (e:employee {name: $name}) remove e.role set e.role = $role return e";

        try (Session session = driver.session()) {
            session.run(query, parameters("name", name, "role", newRole));
            logger.info("Employee updated: " + name);
        } catch (Exception e) {
            logger.error("Error updating employee: " + e.getMessage());
            e.printStackTrace();
        }
    }
//
    // Delete an employee node
    public void deleteEmployee(String name) {
        String query = "use sanjay match (e:department {name: $name}) detach delete e";

        try (Session session = driver.session()) {
            session.run(query, parameters("name", name));
            logger.info("Employee deleted: " + name);
        } catch (Exception e) {
            logger.error("Error deleting employee: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void createDepartment(String name){
        String query = "use sanjay create(d:department{name:$name}) return d";

        try(Session session = driver.session()){
            session.run(query,parameters("name",name));
            logger.info("Department added: "+name);
        } catch (Neo4jException e) {
            logger.error("Failed to add department");
        }
    }

    public void getAllDetails(){
    String query = "use sanjay match(e:employee),(d:department) return e,d";
    try(Session session = driver.session()){
        Result result = session.run(query);
        logger.info("Fetching all details");
        if (result.hasNext()){
            while (result.hasNext()){
                var record = result.next();
            }

        }

    }
    }
}