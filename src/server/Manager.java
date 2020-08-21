package server;

import gui.base.Car;
import gui.base.CreditCardInfo;
import gui.base.Paying;
import gui.base.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Manager {

    private Connection connection;

    public void connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking?useUnicode=true&serverTimezone=UTC", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user){

        try{
            PreparedStatement statement = connection.prepareStatement("" + "INSERT INTO users (id, name, surname, phone_number, gender, id_car, date, VIP) " + "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getGender());
            statement.setString(5, user.getId_car());
            statement.setString(6, user.getDate());
            statement.setString(7, user.isCheck());

            int rows = statement.executeUpdate();

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers(){

        ArrayList<User> userList = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phoneNumber = resultSet.getString("phone_number");
                String gender = resultSet.getString("gender");
                String id_car = resultSet.getString("id_car");
                String date = resultSet.getString("date");
                String check = resultSet.getString("VIP");


                userList.add(new User(id, name, surname, phoneNumber, gender, id_car, date, check));
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return userList;
    }

    public ArrayList<Car> getAllCars(){

        ArrayList<Car> carList = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cars");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String model = resultSet.getString("model");
                String color = resultSet.getString("color");
                String carNumber = resultSet.getString("number");

                carList.add(new Car(id, model, color, carNumber));

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return carList;
    }

    public void addCar(Car car){

        try{
            PreparedStatement statement = connection.prepareStatement("" + "INSERT INTO cars (id, model, color, number) " + "VALUES (NULL, ?, ?, ?)");
            statement.setString(1, car.getModel());
            statement.setString(2, car.getColor());
            statement.setString(3, car.getNumber());

            int rows = statement.executeUpdate();

            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addCreditCard(CreditCardInfo creditCard){

        try {
            PreparedStatement statement = connection.prepareStatement("" + "INSERT INTO credit (number, month, year, name) " + "VALUES (?, ?, ?, ?)");
            statement.setString(1, creditCard.getNumber());
            statement.setString(2, creditCard.getMonth());
            statement.setString(3, creditCard.getYear());
            statement.setString(4, creditCard.getName());

            int rows = statement.executeUpdate();

            statement.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteUser(Paying paying){

        try{
            PreparedStatement statement = connection.prepareStatement("" + "DELETE FROM users WHERE id_car = ?");
            statement.setInt(1, paying.getId());
            int rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteCar(Paying paying){

        try{
            PreparedStatement statement = connection.prepareStatement("" + "DELETE FROM cars WHERE id = ?");
            statement.setInt(1, paying.getId());
            int rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet findUser(User user){
        ResultSet resultSet = null;

        try{
            PreparedStatement statement = connection.prepareStatement("" + "Select * FROM users WHERE id_car = ? AND VIP = ?");
            statement.setString(1, user.getId_car());
            statement.setString(2, user.isCheck());
            resultSet = statement.executeQuery();

        }catch(Exception e){
            e.printStackTrace();
        }

        return resultSet;
    }

}
