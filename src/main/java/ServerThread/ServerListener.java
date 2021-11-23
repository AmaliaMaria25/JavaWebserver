package ServerThread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread {


    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListener.class);

    private int port;
    private String path;
    private ServerSocket serverSocket;

    public ServerListener(int port, String path) throws IOException {
        this.port = port;
        this.path = path;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {
            while(serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                LOGGER.info("Connection accepted: " + socket.getInetAddress());
                ConnectionThread connectionThread = new ConnectionThread(socket,path);
                connectionThread.setDaemon(true);
                connectionThread.run();
            }
        } catch (IOException e) {
            LOGGER.error("Setting socket error", e);
            System.exit(1);
        }finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    LOGGER.error("Could not close port: 10008.");
                    System.exit(1);
                }
            }
        }
    }
}
