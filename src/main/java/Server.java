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
        try ( var serverSocket = new ServerSocket(this.port)) {
            while (true) {
                try ( var socket = serverSocket.accept()) {
                    ExecutorService es = Executors.newFixedThreadPool(FIX_CONST);
                    for (int x = 0;x < FIX_CONST;x++){
                        es.submit(new RunServer(socket));
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}