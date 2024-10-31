package org.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Main{
    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        System.out.println("Hello!!! learning logger. ");
        logger.info("string printed successfully.");
    }
}