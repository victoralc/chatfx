package sample.domain;

import java.util.ArrayList;
import java.util.List;

public class Contacts {

    private List<User> users = new ArrayList<>();

    public void addContact(User user){
        users.add(user);
    }

    public void remove(User user) {
        this.users.remove(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
