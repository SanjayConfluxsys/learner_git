import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerialization {
    public static void main(String[] args) {
        try{
            //creating employee object
            Employee employee = new Employee("John","Pune","Engineering", "Software developer");

            //creating object mapper instance to serialize the object
            ObjectMapper objectMapper = new ObjectMapper();

            //Converting java object to json string(serialization)
            String json = objectMapper.writeValueAsString(employee);
            System.out.println("Serialized JSON: "+json);

            //deserializing the same json
            Employee deserializeEmployee = objectMapper.readValue(json,Employee.class);
            System.out.println("Deserialized: "+deserializeEmployee);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
