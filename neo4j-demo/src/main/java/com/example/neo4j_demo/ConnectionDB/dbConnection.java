package com.example.neo4j_demo.ConnectionDB;
import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.Neo4jException;
import org.neo4j.driver.types.Relationship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class dbConnection {
            
        private static final Logger logger = LoggerFactory.getLogger(dbConnection.class);
        
        private static final String URI = "";
        private static final String USER = "";
        private static final String PASSWORD = "";

//        public static Driver createDriver() {
//            return GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
//        }
//
   public static Driver driver;


    public static Driver createDriver(){
            try{
                driver = GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
                driver.verifyConnectivity();
                logger.info("Successfully connected to database - sanjay");
                return driver;
            } catch (Exception e) {
                logger.error("Error in connecting to db");
                throw new RuntimeException("Failed to connect");
            }

        }




        public static void closeConnection(){
            if (driver!=null){
                try {
                    driver.close();
                    logger.info("Neo4j connection closed");
                }catch (Neo4jException e){
                    logger.error("Error while closing the connection");
                }
            }
        }
    }

//    private final JdbcTemplate jdbcTemplate;
//
//    public dbConnection(String uri, String username, String password) {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        //dataSource.setDriverClassName("org.neo4j.jdbc.bolt.BoltDriver");
//        dataSource.setUrl(uri);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//
//        // Initialize JdbcTemplate with DataSource
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    public JdbcTemplate getJdbcTemplate() {
//        return jdbcTemplate;
//    }
//
//    public void close() {
//
//        }
//    }


//      working fine
//    private static final Logger logger = LoggerFactory.getLogger(dbConnection.class);
//    private static Object people;
//
//    public static void main(String[] args) {
//        final String dbUri = "neo4j://10.1.27.44:7687?database=sanjay";
//        final String dbUser = "neo4j";
//        final String dbPassword = "passw0rd";
//        final String dbName = "sanjay";
//
//        try(Driver driver = GraphDatabase.driver(dbUri,AuthTokens.basic(dbUser,dbPassword));
//            Session session = driver.session()){
//            //Result result = session.run(":use "+dbName);
//            Result result1 = session.run("use sanjay match(e:employee)-[r:worksfor]->(d:department) return e,r,d");
//            logger.info("Connected to the db: {}", dbName);
//            if (result1.hasNext()) {
//                while (result1.hasNext()) {
//                    var record = result1.next(); // Use `var` if there's confusion with `Record`
//                   // var node = record.get("n").asNode(); // Get the node
//                    var employeeNode = record.get("e").asNode();
//                    var departmentNode = record.get("d").asNode();
//                    Relationship relationship = record.get("r").asRelationship();
////                    var workNode = record.get("w").asNode();
////                    var manageNode = record.get("m").asNode();
////                    var emplNode = record.get("x").asNode();
//
//                    logger.info("Employee: "+employeeNode.asMap());
//                    logger.info("Department: "+departmentNode.asMap());
//                    logger.info("Relationship: "+relationship.type());
//
////                    logger.info("worksFor: : "+workNode.asMap());
////                    logger.info("manages: "+ manageNode.asMap());
////                    logger.info("empNode: "+emplNode.asMap());
//
//
//
////                    System.out.println("Employee: "+employeeNode.asMap());
////                    System.out.println("Department: "+departmentNode.asMap());
////                    System.out.println("Labels: " + node.labels());
////                    System.out.println("Properties: " + node.asMap());
//                }
//            } else {
//                System.out.println("No nodes found in the database.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//

