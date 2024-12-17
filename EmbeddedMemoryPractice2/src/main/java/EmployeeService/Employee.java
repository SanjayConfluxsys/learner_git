package EmployeeService;

import org.neo4j.graphdb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Employee {
    private static final Logger logger = LoggerFactory.getLogger(Employee.class);
    private final GraphDatabaseService graphDb;

    public Employee(GraphDatabaseService graphDb) {
        this.graphDb = graphDb;
    }


    // Create a new employee node
    public void createEmployee(String name, String role, String salary) {
        //String query = "use sanjay CREATE (e:employee {name: $name, role: $role, salary: $salary}) RETURN e";
        try (Transaction tx = graphDb.beginTx()) {
            Node node = tx.createNode(Label.label("employee"));
            node.setProperty("name", name);
            node.setProperty("role", role);
            node.setProperty("salary", salary);
            tx.commit();
            logger.info("employee created: {}", node);
            System.out.println("Fetched Employee Node:");
            for (String key : node.getPropertyKeys()) {
                System.out.println(key + ": " + node.getProperty(key));
            }
        } catch (RuntimeException e) {
            logger.error("Error in creating the node {}", e.getMessage());
        }
    }

    // Read employee information by name
    public void getEmployee(String name) {
        try (Transaction tx = graphDb.beginTx()) {
            Node node = tx.createNode(Label.label("employee"));
            node.setProperty("name", name);
            tx.commit();
        } catch (RuntimeException e) {
            logger.error("failed to find the employee: {}",e.getMessage());
        }
    }

    public void updateEmployee(String name, String role, String newRole){
        try(Transaction tx = graphDb.beginTx()){
            Node node = tx.createNode(Label.label("employee"));
            node.removeProperty(role);
            node.setProperty("name",name);
            node.setProperty("newRole",newRole);
            tx.commit();
        } catch (RuntimeException e) {
            logger.error("Failed to update the role: {}",e.getMessage());
        }
    }
}