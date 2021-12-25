package sample;

import javafx.scene.control.ListView;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Client {

    private final Socket socket;
    private final ListView<String> chatListView;

    public Client(ListView<String> chatListView) throws IOException {
        this.socket = new Socket("localhost", 12345);
        this.chatListView = chatListView;
    }

    public void connect() throws IOException, InterruptedException {
        System.out.println("Connecting to server...");

        final var receiveMessageService = new ReceiveMessageService(socket, chatListView);
        receiveMessageService.start();
    }

    public void disconnect() throws IOException {
        System.out.println("Closing socket connection...");
        socket.close();
    }

    public void send(String message) throws IOException {
        final var outputStream = socket.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        printStream.println(message);
    }

}
