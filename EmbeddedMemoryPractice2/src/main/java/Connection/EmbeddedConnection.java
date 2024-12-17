package Connection;

import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.dbms.api.DatabaseManagementServiceBuilder;
import org.neo4j.graphdb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EmbeddedConnection {
    private static final Logger logger = LoggerFactory.getLogger(EmbeddedConnection.class);
    private static final String DB_DIRECTORY = "neo4j-embedded-db"; // Directory to store DB files
    private static final String DEFAULT_DATABASE_NAME = "neo4j"; // Default DB name
    private static DatabaseManagementService managementService;
    private static GraphDatabaseService graphDb;


    //Initialize the embedded Neo4j database with configuration.

    public static void startEmbeddedDatabase() {
        // Set the database directory
        Path databaseDirectory = Paths.get(DB_DIRECTORY);

        // Print the absolute path for verification
        logger.info("Database is being created at: {}", databaseDirectory.toAbsolutePath());

        // Initialize the DatabaseManagementService with database directory
        managementService = new DatabaseManagementServiceBuilder(databaseDirectory).build();
        // Get the default database instance
        graphDb = managementService.database(DEFAULT_DATABASE_NAME);

        // Register shutdown hook to ensure Neo4j shuts down correctly
        registerShutdownHook(managementService);

        logger.info("Embedded Neo4j database started successfully.");
    }


    // Register shutdown hook to gracefully shut down Neo4j when the JVM exits.

    private static void registerShutdownHook(final DatabaseManagementService managementService) {
        // Registers a shutdown hook for the Neo4j instance so that it shuts down properly
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                managementService.shutdown();
                System.out.println("Embedded Neo4j database shut down successfully.");
            }
        });
    }


    //Neo4j GraphDatabaseService instance.

    public static GraphDatabaseService getGraphDb() {
        return graphDb;
    }

    //Shutdown the embedded Neo4j database.
    public static void stopEmbeddedDatabase() {
        if (managementService != null) {
            managementService.shutdown();
            System.out.println("Embedded Neo4j database shut down.");
        }
    }
}
