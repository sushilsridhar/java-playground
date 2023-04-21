package immutables;

/*  making the class final is optional, because the variables are private so it can't be extended and changed,
    add final if you don't want methods to be overridden
*/
public class ImmutableClass {

    private final int noOfWheels;
    private final int engineSize;
    private final int length;
    private final Person person;

    public ImmutableClass(int noOfWheels, int engineSize, int length, Person person) {
        this.noOfWheels = noOfWheels;
        this.engineSize = engineSize;
        this.length = length;
        this.person = person;
    }

    public int getNoOfWheels() {
        return noOfWheels;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public int getLength() {
        return length;
    }

    // to really make the class immutable, create a deep copy of person object and return
    public Person getPerson() {

        int id = person.getId();
        String name = person.getName();

        Person deepCopyOfPerson = new Person(id, name);
        return deepCopyOfPerson;
    }
}

class Person {

    private final int id;
    private final String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
