package sample;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {

    private static final int port = 12345;
    private final ServerSocket serverSocket;
    private final ExecutorService threadPool;
    private final AtomicBoolean isRunning;
    private final List<Socket> sockets = Collections.synchronizedList(new ArrayList<>());

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.threadPool = Executors.newFixedThreadPool(5);
        this.isRunning = new AtomicBoolean(true);
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.run();
    }

    public void run() throws IOException {
        while (isRunning.get()) {
            try {
                var socket = serverSocket.accept();
                sockets.add(socket);
                System.out.println("New client user connection on the port " + socket.getPort());
                threadPool.execute(new ServerMessageManager(socket, this));
            } catch (SocketException e) {
                System.out.println("SocketException: " + e.getMessage());
            }
        }
    }

    public void shutdown() throws IOException {
        isRunning.set(false);
        serverSocket.close();
        threadPool.shutdownNow();
    }

    public void broadcastMessage(String message) throws IOException {
        for (Socket socket : this.sockets) {
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            printStream.println(message);
        }
    }

    public long numberOfConnectedClients() {
        return this.sockets.stream().filter(s -> s.isConnected()).count();
    }
}
