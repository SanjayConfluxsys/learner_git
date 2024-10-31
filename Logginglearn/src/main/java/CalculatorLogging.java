import java.sql.ResultSet;
import java.util.Scanner;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
public class CalculatorLogging {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorLogging.class);

    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        logger.info("Calculator started.");

        while (true){
            logger.info("Enter first number (or 'exit' to quit)");
            String input1 = s.nextLine();
            if (input1.equalsIgnoreCase("exit")){
                logger.info("Calculator exited.");
                break;
            }

            logger.info("Enter operation (+,-,*,/): ");
            String operation = s.nextLine();

            logger.info("Enter second number: ");
            String input2 = s.nextLine();

            try{

                double num1 = Double.parseDouble(input1);
                double num2 = Double.parseDouble(input2);
                double result = 0;

                switch (operation){
                    case "+":
                        result = num1 + num2;
                        logger.debug("Adding {} and {}: {}", num1, num2, result);
                        break;
                    case "-":
                        result = num1 - num2;
                        logger.debug("Subtracting {} and {}: {}", num1, num2, result);
                        break;
                    case "*":
                        result = num1 * num2;
                        logger.debug("Multiplying {} and {}: {}", num1, num2, result);
                        break;
                    case "/":
                        if (num2 == 0){
                            logger.error("Divison by zero error.");
                            System.out.println("Error: Division by zero is not allowed.");
                            continue;
                        }
                        result = num1 / num2;
                        logger.debug("Dividing {} by {}: {}", num1, num2, result);
                        break;
                    default:
                        logger.error("Invalid operation: {}", operation);
                        System.out.println("Error: Invalid operation.");
                        continue;
                }

                logger.info("Result: {}", result);
                //System.out.println("Error: Invalid operation.");

            }  catch (NumberFormatException e) {
                logger.error("Invalid number format: {}", e.getMessage());
                System.out.println("Error: Please enter valid numbers.");
            }
        }

        s.close();
    }
}