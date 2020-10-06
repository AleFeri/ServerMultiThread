import java.net.*;

class ServerMulti {
    String serverName = "[Server Name]";
    int serverPort = -1;

    //costruttori
    public ServerMulti(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    //Metodi
    public void power_up() {
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);

            for(;;) {
                System.out.println("1 Server in attesa ...");
                Socket socket = serverSocket.accept();
                System.out.println("3 Server socket " + socket);
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza de server!");
            System.exit(1);
        }
    }
    //main
    public static void main(String[] args) {
        ServerMulti tcpServer = new ServerMulti("127.0.0.1", 6789);
        tcpServer.power_up();
    }
}