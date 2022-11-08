package reflection;

public class Vehicle {
    String color;
    int wheels;

    // default constructor required for jackson to work
    public Vehicle() { }

    public Vehicle(int wheels, String color) {
        this.wheels = wheels;
        this.color = color;
    }

    // getters and setters required for jackson to work

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }
}
