package Connection;

import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.Neo4jException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RealConnection {

        private static final Logger logger = LoggerFactory.getLogger(RealConnection.class);
//
//        private static final String URI = "";
//        private static final String USER = "neo4j";
//        private static final String PASSWORD = "passw0rd";

   public static Driver driver;

    public static Driver createDriver(){
            try{
                driver = GraphDatabase.driver("URI", AuthTokens.basic("USER", "PASSWORD"));
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

