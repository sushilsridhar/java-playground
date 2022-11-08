package reflection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;

import java.lang.reflect.Field;

public class Basics {
    public static void main(String[] args) throws IllegalAccessException, JsonProcessingException {

        Gson gson = new Gson();

        Person person = new Person(2, "john");
        String json = gson.toJson(person);
        System.out.println("json: "+json);

        Person p = gson.fromJson("{\"age\":2,\"name\":\"john\"}", Person.class);
        System.out.println("obj: "+p.age +" "+ p.name);

        printFields(person);
        printFields(new Vehicle(4, "red"));

        System.out.println("------Jackson------");

        Vehicle vehicle = new Vehicle(4, "white");

        ObjectMapper mapper = new ObjectMapper();
        String pJson = mapper.writeValueAsString(vehicle);
        System.out.println(pJson);

        Vehicle vObjMapper = mapper.readValue("{\"color\":\"white\",\"wheels\":4}", Vehicle.class);
        System.out.println(vObjMapper.wheels+" "+ vObjMapper.color);
    }

    static void printFields(Object o) throws IllegalAccessException {
        Class c = o.getClass();
        for(Field field: c.getDeclaredFields()) {
            String value = "";
            if (field.getType().equals(int.class)) {
                value = String.valueOf(field.getInt(o));
            }

            if (field.getType().equals(String.class)) {
                value = field.get(o).toString();
            }

            System.out.println("Field: " + field.getName() + " value: " + value);
        }
    }
}
