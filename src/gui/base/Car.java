package gui.base;

import java.io.Serializable;

public class Car implements Serializable {
    private int id;
    private String model;
    private String color;
    private String number;

    public Car() {
    }

    public Car(int id, String model, String color, String number) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return id + " " + model + " " + color + " " + number;
    }
}
