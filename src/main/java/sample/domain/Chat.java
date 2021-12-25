package sample.domain;

import java.util.ArrayDeque;
import java.util.Queue;

public class Chat {

    private Queue<String> messages = new ArrayDeque<>();

    public void receive(String message) {
        this.messages.add(message);
    }


}
