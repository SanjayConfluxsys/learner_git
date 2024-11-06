import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDeSerialization {
    public static void main(String[] args) {
        try{
            //sample json
            String json = "{\"name\":\"John Doe\",\"city\":\"New York\",\"department\":\"Engineering\",\"designation\":\"Software Developer\"}";

            //objectmapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            //conversion json string to java object
            Employee employee = objectMapper.readValue(json,Employee.class);
            System.out.println(employee);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}