package gui.base;

import java.io.Serializable;

public class  User implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String gender;
    private String id_car;
    private String date;
    private String check;

    public User() {
    }

    public User(int id, String name, String surname, String phoneNumber, String gender, String id_car, String date,String check) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.id_car = id_car;
        this.date = date;
        this.check = check;
    }

    public String isCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public User(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId_car() {
        return id_car;
    }

    public void setId_car(String id_car) {
        this.id_car = id_car;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return  id + " " + name + " " + surname + " " + phoneNumber + " " + gender + " " + id_car + " " + date;
    }
}
