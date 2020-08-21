package server;

import gui.base.*;
import pdf.CreatePDF;
import pdf.Receipt;

import java.net.*;
import java.io.*;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ServerRunnable extends Thread {
    private Socket socket;
    public ServerRunnable(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {

            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            Manager manager = new Manager();
            manager.connect();
            Request request = new Request();

            while ((request = (Request) inStream.readObject()) != null) {
                if (request.getOperationType().equals("ADD_USER")) {
                    User user = request.getUser();
                    manager.addUser(user);
                    System.out.println("ADD USER OPERATION DONE");

                } else if (request.getOperationType().equals("DISCONNECT")) {
                    System.out.println("DISCONNECTING..");
                    System.exit(0);
                    break;

                } else if (request.getOperationType().equals("ADD_CAR")) {
                    Car car = request.getCar();
                    manager.addCar(car);
                    ArrayList<Car> cars = manager.getAllCars();
                    saveIdCar(cars.get(cars.size() - 1).getId());
                    outStream.writeObject(getIdCar());
                    System.out.println("ADD CAR OPERATION DONE");

                } else if (request.getOperationType().equals("DATE")) {

                    try {
                        Paying paying = request.getPaying();
                        ArrayList<User> users = manager.getAllUsers();
                        paying.setUser(users);

                        for(int i = 0; i < paying.getUsers().size(); i++){
                            if(paying.getUsers().get(i).isCheck().equals("yes")){
                                if(getDateDiff(ConvertToDate(paying.getUsers().get(i).getDate()), new Date(), TimeUnit.DAYS) > 30) {
                                    manager.deleteUser(paying);
                                    manager.deleteCar(paying);
                                }
                            }
                        }

                        CreatePDF.createTemplate();
                        for (int i = 0; i < paying.getUsers().size(); i++) {
                            if (Byte.parseByte(paying.getUsers().get(i).getId_car()) == paying.getId()) {
                                StaticVariable.checkCarId = true;
                                if (paying.getUsers().get(i).isCheck().equals("no")) {
                                    if (getDateDiff(ConvertToDate(paying.getUsers().get(i).getDate()), new Date(), TimeUnit.MINUTES) >= 15) {
                                        Receipt receipt = new Receipt("Thank you for using our parking! Be careful " +
                                                "on the road! Have a nice day! :)",
                                                (int) getDateDiff(ConvertToDate(paying.getUsers().get(i).getDate()), new Date(), TimeUnit.MINUTES) / 5 * 10,
                                                users.get(i).getName() + " " + users.get(i).getSurname());
                                        CreatePDF.fillInReceipt(receipt);
                                    } else {
                                        Receipt receipt = new Receipt("Thank you for using our parking! Be careful " +
                                                "on the road! Have a nice day! :)", 0, users.get(i).getName() + " " + users.get(i).getSurname());
                                        CreatePDF.fillInReceipt(receipt);
                                    }
                                    manager.deleteUser(paying);
                                    manager.deleteCar(paying);

                                } else if (paying.getUsers().get(i).isCheck().equals("yes")) {
                                    Receipt receipt = new Receipt("Thank you for using our parking! Be careful " +
                                            "on the road! Have a nice day! :)", 0, users.get(i).getName() + " " + users.get(i).getSurname());
                                    CreatePDF.fillInReceipt(receipt);
                                }
                            }
                            else
                                StaticVariable.checkCarId = false;
                        }
                    if (StaticVariable.checkCarId == false)
                        System.out.println("NO CAR ID");
                    else
                        System.out.println("DATE CARD OPERATION DONE");

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

                else if(request.getOperationType().equals("CREDIT")){
                    CreditCardInfo creditCard = request.getCreditCard();
                    manager.addCreditCard(creditCard);
                    System.out.println("CREDIT CARD OPERATION DONE");
                }

                else if(request.getOperationType().equals("ADD_VIP")){
                    User user = new User();
                    String id = request.getId();
                    user.setId_car(id);
                    user.setCheck("yes");
                    ResultSet result = manager.findUser(user);
                    int counter = 0;
                    try {
                        while (result.next()) {
                            counter++;
                            break;
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    if(counter != 1) {
                        System.out.println("NO PRIME CAR ID");
                    }
                }
            }

            inStream.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Date ConvertToDate(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }
    public static void saveIdCar(int id) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("memory.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(id);
            objectOutputStream.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static int getIdCar() {
        int id = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream("memory.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            id = (int) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return id;
    }
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}