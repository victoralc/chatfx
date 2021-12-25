package sample;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ReceiveMessageService extends Service<Void> {

    private final Socket socket;
    private final ListView<String> chatListView;

    public ReceiveMessageService(Socket socket, ListView<String> chatListView) {
        this.socket = socket;
        this.chatListView = chatListView;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() {
                final Scanner serverScanner;
                try {
                    serverScanner = new Scanner(socket.getInputStream());
                    while (serverScanner.hasNextLine()) {
                        final var message = serverScanner.nextLine();
                        Platform.runLater(() -> chatListView.getItems().add(MessageDisplay.display(message)));
                    }
                    serverScanner.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        };

    }
}
