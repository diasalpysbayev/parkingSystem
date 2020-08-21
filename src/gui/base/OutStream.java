package gui.base;

import gui.Main;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class OutStream{

    private static ObjectOutputStream outStream;

    static {
        try {
            outStream = new ObjectOutputStream(Main.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeRequest(String requestType, User user) throws IOException {
        outStream.writeObject(new Request(requestType, user));
    }

    public void writeRequestCar(String requestType, Car car) throws IOException {
        outStream.writeObject(new Request(requestType, car));
    }

    public void writeRequestCreditCard(String requestType, CreditCardInfo creditCard) throws IOException {
        outStream.writeObject(new Request(requestType, creditCard));
    }

    public void writeRequestDate(String requestType, Paying paying) throws IOException {
        outStream.writeObject(new Request(requestType, paying));
    }

    public void writeRequestVip(String requestType, String id) throws IOException {
        outStream.writeObject(new Request(requestType, id));
    }
}

