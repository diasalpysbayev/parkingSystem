package gui.base;

import java.io.Serializable;
import java.util.ArrayList;

public class Paying implements Serializable {
    private ArrayList<User> users;
    private int id;

    public Paying(ArrayList<User> users, int id) {
        this.users = users;
        this.id = id;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUser(ArrayList<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
