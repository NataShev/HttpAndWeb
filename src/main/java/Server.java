import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {
    final int FIX_CONST = 64;

    private int port;

    public Server(int port) {
        this.port = port;
    }

    void launch() {
        try (final var serverSocket = new ServerSocket(this.port)) {
            while (true) {
                try (final var socket = serverSocket.accept()) {
                    ExecutorService es = Executors.newFixedThreadPool(FIX_CONST);
                    final Future<?> submit = es.submit(new RunServer(socket));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}