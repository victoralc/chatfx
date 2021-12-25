package sample;

import java.net.Socket;
import java.util.Scanner;

public class ServerMessageManager implements Runnable {

    private final Socket socket;
    private final Server server;

    public ServerMessageManager(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            while (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                this.server.broadcastMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
