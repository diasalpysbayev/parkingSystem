package gui.base;

import java.io.Serializable;

public class Request implements Serializable {
    private String operationType;
    private User user;
    private Car car;
    private CreditCardInfo creditCard;
    private String id;
    private Paying paying;

    public Request() {
    }

    public Request(String operationType, Car car) {
        this.operationType = operationType;
        this.car = car;
    }

    public Request(String operationType, User user) {
        this.operationType = operationType;
        this.user = user;
    }

    public Request(String operationType, CreditCardInfo creditCard) {
        this.operationType = operationType;
        this.creditCard = creditCard;
    }

    public Request(String operationType, Paying paying) {
        this.operationType = operationType;
        this.paying = paying;
    }

    public Request(String operationType, String id) {
        this.operationType = operationType;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Paying getPaying() {
        return paying;
    }

    public void setPaying(Paying paying) {
        this.paying = paying;
    }

    public CreditCardInfo getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardInfo creditCard) {
        this.creditCard = creditCard;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
