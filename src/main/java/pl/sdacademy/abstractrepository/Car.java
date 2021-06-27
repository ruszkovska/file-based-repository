package pl.sdacademy.abstractrepository;

public class Car implements Entity{
    Integer id;
    int maxSpeed;
    String model;
    String color;

    public Car setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public Integer getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public Car(int maxSpeed, String model, String color) {
        this.maxSpeed = maxSpeed;
        this.model = model;
        this.color = color;
    }

    public Car(Integer id, int maxSpeed, String model, String color) {
        this.id = id;
        this.maxSpeed = maxSpeed;
        this.model = model;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", maxSpeed=" + maxSpeed +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
